package startup.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startup.common.dto.GameDto;
import startup.common.enumeration.GameType;
import startup.model.Game;
import startup.persistence.GameRepository;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional
    public GameDto createNewGame() {
        final Game game = new Game.Builder()
                .withType(GameType.TEXAS_HOLDEM)
                .withCode("Test")
                .withWinsRequired(3)
                .build();

        this.gameRepository.persist(game);

        return new GameDto.Builder().withGuid(game.getGuid()).build();
    }
}
