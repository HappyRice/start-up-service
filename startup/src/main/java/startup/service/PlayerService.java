package startup.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startup.common.dto.PlayerDto;
import startup.exception.GameNotFoundException;
import startup.model.Game;
import startup.model.Player;
import startup.persistence.PlayerRepository;
import startup.transformer.PlayerTransformer;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final GameService gameService;

    private static final Logger LOGGER = LogManager.getLogger(PlayerService.class);

    public PlayerService(final GameService gameService, final PlayerRepository playerRepository) {
        this.gameService = gameService;
        this.playerRepository = playerRepository;
    }

    @Transactional
    public PlayerDto joinGame(final String gameGuid, final String name) throws GameNotFoundException{
        LOGGER.info("Creating new player to join game with guid: [{}]...", gameGuid);

        final Game game = this.gameService.getGameByGuid(gameGuid);

        if (game == null) {
            LOGGER.warn("No game found for gameGuid: [{}]", gameGuid);
            throw new GameNotFoundException();
        }

        final Player player = Player.builder()
                .withName(name)
                .withGame(game)
                .build();

        this.playerRepository.persist(player);

        return PlayerTransformer.buildPlayerDto(player);
    }
}
