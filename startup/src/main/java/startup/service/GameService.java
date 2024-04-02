package startup.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startup.common.dto.GameDto;
import startup.common.enumeration.GameType;
import startup.model.Game;
import startup.persistence.GameRepository;
import startup.transformer.GameTransformer;

@Service
public class GameService {

    private final GameRepository gameRepository;

    private static final Logger LOGGER = LogManager.getLogger(GameService.class);

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
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
}
