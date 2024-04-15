package startup.service;

import startup.common.dto.GameDto;
import startup.common.dto.PlayerDto;
import startup.exception.GameNotFoundException;

public interface PlayerService {

    PlayerDto joinGame(final String code, final String name) throws GameNotFoundException;

    GameDto test(final String playerGuid);

}
