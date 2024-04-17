package startup.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startup.common.dto.GameDto;
import startup.common.enumeration.GameType;
import startup.exception.GameNotFoundException;
import startup.model.Game;
import startup.model.GameSetting;
import startup.persistence.GameRepository;
import startup.transformer.GameTransformer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static startup.common.enumeration.GameState.CREATED;
import static startup.common.enumeration.GameType.TEXAS_HOLDEM;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    private static final Logger LOGGER = LogManager.getLogger(GameServiceImpl.class);

    public GameServiceImpl(final GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game getGameByGuid(final String guid) throws GameNotFoundException {
        return Optional.ofNullable(this.gameRepository.getGameByGuid(guid)).orElseThrow(() -> {
            LOGGER.warn("No game found for guid: [{}]", guid);
            return new GameNotFoundException();
        });
    }

    @Override
    public Game getGameByCode(final String code) throws GameNotFoundException {
        return Optional.ofNullable(this.gameRepository.getGameByCode(code)).orElseThrow(() -> {
            LOGGER.warn("No game found for code: [{}]", code);
            return new GameNotFoundException();
        });
    }

    @Override
    public void saveGame(final Game game) {
        this.gameRepository.persist(game);
    }

    @Override
    @Transactional
    public GameDto createNewGame() {
        LOGGER.info("Creating new game...");

        final Game game = Game.builder()
                .withCode(RandomStringUtils.random(25, true, true))
                .withState(CREATED)
                .withSetting(GameSetting.builder()
                        .withGameType(TEXAS_HOLDEM)
                        .withWinsRequired(1)
                        .build()
                )
                .build();

        game.getSetting().setGame(game);

        this.gameRepository.persist(game);

        return GameTransformer.buildGameDto(game);
    }

    @Override
    public GameDto getStatus(final String code) throws GameNotFoundException {
        LOGGER.info("Getting status of game with code: [{}]...", code);

        final Game game = this.getGameByCode(code);

        return GameTransformer.buildGameDtoWithPlayersAndHand(game);
    }

    @Override
    public List<String> getTypes() {
        return Arrays.stream(GameType.values()).map(GameType::getDescription).collect(Collectors.toList());
    }
}
