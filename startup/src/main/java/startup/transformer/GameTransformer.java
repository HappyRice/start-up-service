package startup.transformer;

import startup.common.dto.GameDto;
import startup.model.Game;

import java.util.stream.Collectors;

public final class GameTransformer {

    private GameTransformer() {
        // Prevent Instantiation
    }

    public static GameDto buildGameDtoWithSettingsAndPlayers(final Game game) {
        return GameDto.builder()
                .withCode(game.getCode())
                .withState(game.getState())
                .withSetting(GameSettingTransformer.buildGameSettingDto(game.getSetting()))
                .withActiveDate(game.getActiveDate())
                .withPlayers(game.getPlayers().stream().map(PlayerTransformer::buildSimplePlayerDto).collect(Collectors.toList()))
                .build();
    }

    public static GameDto buildGameDtoWithSettings(final Game game) {
        return GameDto.builder()
                .withGuid(game.getGuid())
                .withCode(game.getCode())
                .withState(game.getState())
                .withSetting(GameSettingTransformer.buildGameSettingDto(game.getSetting()))
                .withActiveDate(game.getActiveDate())
                .build();
    }

}