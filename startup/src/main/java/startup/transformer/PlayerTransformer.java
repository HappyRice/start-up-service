package startup.transformer;

import startup.common.dto.GameDto;
import startup.common.dto.PlayerDto;
import startup.model.Game;
import startup.model.Player;

public final class PlayerTransformer {

    private PlayerTransformer() {
        // Prevent Instantiation
    }

    public static PlayerDto buildPlayerDto(final Player player) {
        return PlayerDto.builder()
                .withId(player.getId())
                .withGuid(player.getGuid())
                .withName(player.getName())
                .withWinCounter(player.getWinCounter())
                .withGame(GameTransformer.buildGameDto(player.getGame()))
                .build();
    }

}