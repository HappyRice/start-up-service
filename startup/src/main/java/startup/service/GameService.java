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
import startup.persistence.GameRepository;
import startup.transformer.GameTransformer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public GameDto createNewGame() {
        LOGGER.info("Creating new game...");

        final Game game = Game.builder()
                .withType(GameType.TEXAS_HOLDEM)
                .withCode(RandomStringUtils.random(25, true, true))
                .withWinsRequired(1)
                .build();

        this.gameRepository.persist(game);

        return GameTransformer.buildGameDto(game);
    }

    public GameDto getStatus(final String code) throws GameNotFoundException {
        LOGGER.info("Getting status of game with code: [{}]...", code);

        final Game game = this.getGameByCode(code);

        if (game == null || game.getStartDate() != null) {
            LOGGER.warn("No game found for code: [{}]", code);
            throw new GameNotFoundException();
        }

        return GameTransformer.buildGameDtoWithPlayers(game);
    }

    public List<String> getTypes() {
        return Arrays.stream(GameType.values()).map(GameType::getDescription).collect(Collectors.toList());
    }
}
