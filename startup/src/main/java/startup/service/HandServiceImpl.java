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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static startup.common.enumeration.GameState.*;
import static startup.common.enumeration.HandState.ACTIVE;

@Service
public class HandServiceImpl implements HandService {

    private static final Logger LOGGER = LogManager.getLogger(HandServiceImpl.class);
    private static final int TOP_CARD_INDEX = 0;

    private final HandRepository handRepository;

    private final GameService gameService;

    public HandServiceImpl(final HandRepository handRepository, final GameService gameService) {
        this.handRepository = handRepository;
        this.gameService = gameService;
    }

    @Override
    @Transactional
    public HandDto createNewHand(final String gameGuid) throws GameNotFoundException {
        final Game game = this.gameService.getGameByGuid(gameGuid);

        // Check game status
        if (game.getState() == ENDED) {
            LOGGER.warn("No game found in valid status for guid: [{}]", gameGuid);
            throw new GameNotFoundException();
        } else if (game.getState() == CREATED) {
            LOGGER.info("Game started for guid: [{}]", gameGuid);
            game.setActiveDate(LocalDateTime.now());
            game.setState(IN_PROGRESS);
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

    @Override
    @Transactional
    public HandDto createFlop(final String handGuid) throws HandNotFoundException {
        final Hand hand = this.getActiveHandByGuid(handGuid);

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
        hand.setCards(flop3.getRight());

        this.handRepository.persist(hand);

        return HandTransformer.buildHandDto(hand);
    }

    @Override
    @Transactional
    public HandDto createTurn(final String handGuid) throws HandNotFoundException {
        final Hand hand = this.getActiveHandByGuid(handGuid);

        LOGGER.info("Creating turn for hand with guid: [{}]", hand.getGuid());

        final Pair<Card,List<Card>> turn = this.dealNextCard(hand.getCards());

        final Board board = hand.getBoard();

        board.setTurn(turn.getLeft());
        hand.setCards(turn.getRight());

        this.handRepository.persist(hand);

        return HandTransformer.buildHandDto(hand);
    }

    @Override
    @Transactional
    public HandDto createRiver(final String handGuid) throws HandNotFoundException {
        final Hand hand = this.getActiveHandByGuid(handGuid);

        LOGGER.info("Creating river for hand with guid: [{}]", hand.getGuid());

        final Pair<Card,List<Card>> river = this.dealNextCard(hand.getCards());

        final Board board = hand.getBoard();

        board.setRiver(river.getLeft());
        hand.setCards(river.getRight());

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

    private Hand getActiveHandByGuid(final String guid) throws HandNotFoundException{
        final Hand hand = this.handRepository.getActiveHandByGuid(guid);

        if (hand == null) {
            LOGGER.warn("No hand found in ACTIVE status for guid: [{}]", guid);
            throw new HandNotFoundException();
        }

        return hand;
    }
}
