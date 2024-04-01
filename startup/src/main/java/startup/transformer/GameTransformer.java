package startup.transformer;

import startup.common.dto.GameDto;
import startup.model.Game;

public final class GameTransformer {

    private GameTransformer() {
        // prevent instantiation
    }

    public static GameDto buildGameDto(final Game game) {
        return new GameDto.Builder()
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