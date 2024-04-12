package startup.service;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startup.common.dto.HandDto;
import startup.common.enumeration.Card;
import startup.exception.GameNotFoundException;
import startup.exception.HandNotFoundException;
import startup.model.*;
import startup.persistence.HandRepository;
import startup.transformer.HandTransformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static startup.common.enumeration.GameState.IN_PROGRESS;
import static startup.common.enumeration.HandState.ACTIVE;

@Service
public class HandService {

    private static final Logger LOGGER = LogManager.getLogger(HandService.class);
    private static final int TOP_CARD_INDEX = 0;

    private final HandRepository handRepository;

    private final GameService gameService;

    public HandService(final HandRepository handRepository, final GameService gameService) {
        this.handRepository = handRepository;
        this.gameService = gameService;
    }

    public Hand getHandByGuid(final String guid) {
        return this.handRepository.getHandByGuid(guid);
    }

    @Transactional
    public HandDto createNewHand(final String gameGuid) throws GameNotFoundException {
        final Game game = this.gameService.getGameByGuid(gameGuid);

        if (game == null || game.getState() != IN_PROGRESS) {
            LOGGER.warn("No game found in IN_PROGRESS status for guid: [{}]", gameGuid);
            throw new GameNotFoundException();
        }

        LOGGER.info("Creating new hand for game with guid: [{}]", game.getGuid());

        final Hand hand = Hand.builder()
                .withGame(game)
                .withState(ACTIVE)
                .build();

        game.setCurrentHand(hand);

        final Pair<List<PlayerHand>,List<Card>> result = this.dealPlayerHands(game);

        hand.setCards(result.getRight());
        hand.setPlayerHands(result.getLeft());

        this.gameService.saveGame(game);

        return HandTransformer.buildHandDto(hand);
    }

    @Transactional
    public HandDto createFlop(final String handGuid) throws HandNotFoundException {
        final Hand hand = this.getHandByGuid(handGuid);

        if (hand == null || hand.getState() != ACTIVE) {
            LOGGER.warn("No hand found in ACTIVE status for guid: [{}]", handGuid);
            throw new HandNotFoundException();
        }

        LOGGER.info("Creating flop for hand with guid: [{}]", hand.getGuid());

        final Pair<Card,List<Card>> flop1 = this.dealNextCard(hand.getCards());
        final Pair<Card,List<Card>> flop2 = this.dealNextCard(flop1.getRight());
        final Pair<Card,List<Card>> flop3 = this.dealNextCard(flop2.getRight());

        final Board board = Board.builder()
                .withHand(hand)
                .withFlop1(flop1.getLeft())
                .withFlop2(flop2.getLeft())
                .withFlop3(flop3.getLeft())
                .build();

        hand.setBoard(board);

        this.handRepository.persist(hand);

        return HandTransformer.buildHandDto(hand);
    }

    private Pair<List<PlayerHand>, List<Card>> dealPlayerHands(final Game game) {
        final List<PlayerHand> playerHands = new ArrayList<>();
        final List<Card> cards = new ArrayList<>(shuffleCards(Arrays.asList(Card.values())));

        for (final Player player: game.getPlayers()) {
            final PlayerHand playerHand = PlayerHand.builder()
                    .withHand(game.getCurrentHand())
                    .withPlayer(player)
                    .withCard1(cards.remove(TOP_CARD_INDEX))
                    .withCard2(cards.remove(TOP_CARD_INDEX))
                    .build();

            playerHands.add(playerHand);
        }

        return Pair.of(playerHands, cards);
    }

    private Pair<Card, List<Card>> dealNextCard(final List<Card> cards) {
        this.shuffleCards(cards);

        return Pair.of(cards.remove(TOP_CARD_INDEX), cards);
    }

    private List<Card> shuffleCards(final List<Card> cards) {
        Collections.shuffle(cards);

        return cards;
    }
}
