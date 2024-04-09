package startup.transformer;

import startup.common.dto.GameSettingDto;
import startup.model.GameSetting;

public final class GameSettingTransformer {

    private GameSettingTransformer() {
        // Prevent Instantiation
    }

    public static GameSettingDto buildGameSettingDto(final GameSetting setting) {
        return GameSettingDto.builder()
                .withType(setting.getType())
                .withWinsRequired(setting.getWinsRequired())
                .build();
    }

}