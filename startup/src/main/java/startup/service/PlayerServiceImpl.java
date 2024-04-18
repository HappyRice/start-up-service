package startup.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startup.common.dto.GameDto;
import startup.common.dto.PlayerDto;
import startup.exception.GameNotFoundException;
import startup.model.Game;
import startup.model.Player;
import startup.persistence.PlayerRepository;
import startup.transformer.GameTransformer;
import startup.transformer.PlayerTransformer;

import java.util.Optional;

import static startup.common.enumeration.GameState.CREATED;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final GameService gameService;

    private static final Logger LOGGER = LogManager.getLogger(PlayerServiceImpl.class);

    public PlayerServiceImpl(final GameService gameService, final PlayerRepository playerRepository) {
        this.gameService = gameService;
        this.playerRepository = playerRepository;
    }

    @Override
    @Transactional
    public PlayerDto joinGame(final String code, final String name) throws GameNotFoundException {
        LOGGER.info("Creating new player to join game with code: [{}]...", code);

        final Game game = this.gameService.getGameByCode(code);

        if (game.getState() != CREATED) {
            LOGGER.warn("No game found in CREATED status for code: [{}]", code);
            throw new GameNotFoundException();
        }

        final Player player = Player.builder()
                .withName(name)
                .withGame(game)
                .withWinCounter(0)
                .build();

        this.playerRepository.persist(player);

        return PlayerTransformer.buildPlayerDto(player);
    }

    @Override
    @Transactional
    public GameDto test(final String playerGuid) {
        final Optional<Player> player = this.getPlayerByGuid(playerGuid);
        final Game game = player.get().getGame();
        game.getSetting().setWinsRequired(game.getSetting().getWinsRequired() + 1);

        return GameTransformer.buildGameDtoWithPlayersAndHand(player.get().getGame());
    }

    private Optional<Player> getPlayerByGuid(final String guid) {
        return this.playerRepository.getPlayerByGuid(guid);
    }
}
