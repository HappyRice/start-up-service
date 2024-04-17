package startup.transformer;

import startup.common.dto.HandDto;
import startup.model.Hand;

import java.util.Optional;

public final class HandTransformer {

    private HandTransformer() {
        // Prevent Instantiation
    }

    public static HandDto buildHandDto(final Hand hand) {
        return Optional.ofNullable(hand).map(h -> HandDto.builder()
                .withState(hand.getState())
                .withBoard(BoardTransformer.buildBoardDto(hand.getBoard()))
                .build()).orElse(null);
    }

}