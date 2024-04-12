package startup.transformer;

import startup.common.dto.HandDto;
import startup.model.Hand;

public final class HandTransformer {

    private HandTransformer() {
        // Prevent Instantiation
    }

    public static HandDto buildHandDto(final Hand hand) {
        if (hand != null) {
            return HandDto.builder()
                    .withState(hand.getState())
                    .withBoard(BoardTransformer.buildBoardDto(hand.getBoard()))
                    .build();
        } else {
            return null;
        }
    }

}