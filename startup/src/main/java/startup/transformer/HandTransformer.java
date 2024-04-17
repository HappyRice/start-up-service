package startup.transformer;

import startup.common.dto.HandDto;
import startup.model.Hand;

import java.util.Optional;

public final class HandTransformer {

    private HandTransformer() {
        // Prevent Instantiation
    }

    public static HandDto buildHandDto(final Hand hand) {
        final Optional<Hand> handOpt = Optional.ofNullable(hand);

        if (handOpt.isPresent()) {
            return HandDto.builder()
                    .withState(hand.getState())
                    .withBoard(BoardTransformer.buildBoardDto(hand.getBoard()))
                    .build();
        } else {
            return null;
        }
    }

}