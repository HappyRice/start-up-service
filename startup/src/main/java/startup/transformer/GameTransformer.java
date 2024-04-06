package startup.transformer;

import startup.common.dto.GameDto;
import startup.model.Game;

import java.util.stream.Collectors;

public final class GameTransformer {

    private GameTransformer() {
        // Prevent Instantiation
    }

    public static GameDto buildGameDtoWithPlayers(final Game game) {
        return GameDto.builder()
                .withId(game.getId())
                .withCode(game.getCode())
                .withType(game.getType())
                .withStartDate(game.getStartDate())
                .withEndDate(game.getEndDate())
                .withWinsRequired(game.getWinsRequired())
                .withPlayers(game.getPlayers().stream().map(PlayerTransformer::buildSimplePlayerDto).collect(Collectors.toList()))
                .build();
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