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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static startup.common.enumeration.GameState.CREATED;
import static startup.common.enumeration.GameState.IN_PROGRESS;
import static startup.common.enumeration.GameType.TEXAS_HOLDEM;

@Service
public class GameService {

    private final GameRepository gameRepository;

    private static final Logger LOGGER = LogManager.getLogger(GameService.class);

    public GameService(final GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game getGameByGuid(final String guid) {
        return this.gameRepository.getGameByGuid(guid);
    }

    public Game getGameByCode(final String code) {
        return this.gameRepository.getGameByCode(code);
    }

    public void saveGame(final Game game) {
        this.gameRepository.persist(game);
    }

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

    @Transactional
    public GameDto startGame(final String guid) throws GameNotFoundException {
        final Game game = this.getGameByGuid(guid);

        if (game == null || game.getState() != CREATED) {
            LOGGER.warn("No game found in CREATED status for guid: [{}]", guid);
            throw new GameNotFoundException();
        }

        LOGGER.info("Starting game for guid: [{}]", guid);

        game.setState(IN_PROGRESS);
        game.setActiveDate(LocalDateTime.now());

        this.gameRepository.persist(game);

        return GameTransformer.buildGameDtoWithPlayersAndHand(game);
    }

    public GameDto getStatus(final String code) throws GameNotFoundException {
        LOGGER.info("Getting status of game with code: [{}]...", code);

        final Game game = this.getGameByCode(code);

        if (game == null) {
            LOGGER.warn("No game found for code: [{}]", code);
            throw new GameNotFoundException();
        }

        return GameTransformer.buildGameDtoWithPlayersAndHand(game);
    }

    public List<String> getTypes() {
        return Arrays.stream(GameType.values()).map(GameType::getDescription).collect(Collectors.toList());
    }

    public void foo (final List<String> data) {

    }

    public void foo (final ArrayList<Integer> data) {

    }


}
