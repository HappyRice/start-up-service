package startup.service;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startup.model.Game;
import startup.model.Hand;
import startup.model.Player;
import startup.model.PlayerHand;
import startup.model.enumeration.Card;
import startup.persistence.HandRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static startup.common.enumeration.HandState.ACTIVE;

@Service
public class HandService {

    private final HandRepository handRepository;

    private static final Logger LOGGER = LogManager.getLogger(HandService.class);

    public HandService(final HandRepository handRepository) {
        this.handRepository = handRepository;
    }

    @Transactional
    public void createNewHand(final Game game) {
        LOGGER.info("Creating new hand for game code: [{}]", game.getCode());

        final Hand hand = Hand.builder()
                .withGame(game)
                .withState(ACTIVE)
                .build();

        game.setCurrentHand(hand);

        final Pair<List<PlayerHand>,List<Card>> result = this.dealPlayerHands(game);

        hand.setCards(result.getRight());
        hand.setPlayerHands(result.getLeft());

        this.handRepository.persist(hand);
    }

    private Pair<List<PlayerHand>, List<Card>> dealPlayerHands(final Game game) {
        final List<PlayerHand> playerHands = new ArrayList<>();
        final List<Card> cards = new ArrayList<>(shuffleCards(Arrays.asList(Card.values())));

        for (final Player player: game.getPlayers()) {
            final PlayerHand playerHand = PlayerHand.builder()
                    .withHand(game.getCurrentHand())
                    .withPlayer(player)
                    .withCard1(cards.remove(0))
                    .withCard2(cards.remove(0))
                    .build();

            playerHands.add(playerHand);
        }

        return Pair.of(playerHands, cards);
    }

    private List<Card> shuffleCards(final List<Card> cards) {
        Collections.shuffle(cards);

        return cards;
    }
}
