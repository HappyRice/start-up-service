package startup.transformer;

import org.junit.Test;
import startup.common.dto.GameDto;
import startup.common.enumeration.GameType;
import startup.model.Game;
import startup.model.Player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GameTransformerTest {

	@Test
	public void testTransformGame() {
		final Game game = Game.builder()
				.withCode("ABC123")
				.withType(GameType.TEXAS_HOLDEM)
				.withWinsRequired(3)
				.build();

		final GameDto gameDto = GameTransformer.buildGameDto(game);

		assertEquals("ABC123", gameDto.getCode());
		assertEquals(GameType.TEXAS_HOLDEM, gameDto.getType());
		assertEquals(Integer.valueOf(3), gameDto.getWinsRequired());
		assertNull(gameDto.getStartDate());
		assertNull(gameDto.getEndDate());
	}

	@Test
	public void testTransformGameWithPlayers() {
		final Game game = Game.builder()
				.withCode("ABC123")
				.withType(GameType.TEXAS_HOLDEM)
				.withWinsRequired(3)
				.build();

		final Player player = Player.builder()
				.withGame(game)
				.withWinCounter(0)
				.withName("Bob")
				.build();

		game.getPlayers().add(player);

		final GameDto gameDto = GameTransformer.buildGameDtoWithPlayers(game);

		assertEquals("ABC123", gameDto.getCode());
		assertEquals(GameType.TEXAS_HOLDEM, gameDto.getType());
		assertEquals(Integer.valueOf(3), gameDto.getWinsRequired());
		assertNull(gameDto.getStartDate());
		assertNull(gameDto.getEndDate());
		assertEquals("Bob", gameDto.getPlayers().get(0).getName());
		assertEquals(0, gameDto.getPlayers().get(0).getWinCounter());
	}
}
