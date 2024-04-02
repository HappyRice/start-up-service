package startup.transformer;

import startup.common.dto.GameDto;
import startup.model.Game;

public final class GameTransformer {

    private GameTransformer() {
        // Prevent Instantiation
    }

    public static GameDto buildGameDto(final Game game) {
        return GameDto.builder()
                .withId(game.getId())
                .withGuid(game.getGuid())
                .withCode(game.getCode())
                .withType(game.getType())
                .withStartDate(game.getStartDate())
                .withEndDate(game.getEndDate())
                .withWinsRequired(game.getWinsRequired())
                .build();
    }

}