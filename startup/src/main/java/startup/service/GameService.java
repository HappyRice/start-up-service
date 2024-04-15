package startup.service;

import startup.common.dto.GameDto;
import startup.exception.GameNotFoundException;
import startup.model.Game;

import java.util.List;

public interface GameService {

    Game getGameByGuid(final String guid) throws GameNotFoundException;

    Game getGameByCode(final String code) throws GameNotFoundException;

    void saveGame(final Game game);

    GameDto createNewGame();

    GameDto getStatus(final String code) throws GameNotFoundException;

    List<String> getTypes();

}
