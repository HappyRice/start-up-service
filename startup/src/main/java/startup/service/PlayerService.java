package startup.service;

import startup.common.dto.GameDto;
import startup.common.dto.PlayerDto;
import startup.exception.GameNotFoundException;

public interface PlayerService {

    /**
     * Joins a game in CREATED status by creating a player with given name
     * @param code - the code of the game
     * @param name - the name of the player
     * @return the created player
     */
    PlayerDto joinGame(final String code, final String name) throws GameNotFoundException;

    GameDto test(final String playerGuid);

}
