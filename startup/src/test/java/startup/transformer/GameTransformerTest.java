package startup.transformer;

import org.junit.Test;
import startup.common.dto.GameDto;
import startup.model.Game;
import startup.model.GameSetting;
import startup.model.Player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static startup.common.enumeration.GameState.CREATED;
import static startup.common.enumeration.GameType.TEXAS_HOLDEM;

public class GameTransformerTest {

	@Test
	public void testTransformGame() {
		final Game game = Game.builder()
				.withCode("ABC123")
				.withState(CREATED)
				.withSetting(
						GameSetting.builder()
								.withGameType(TEXAS_HOLDEM)
								.withWinsRequired(3)
								.build()
				)
				.build();

		final GameDto gameDto = GameTransformer.buildGameDto(game);

		assertEquals("ABC123", gameDto.getCode());
		assertEquals(CREATED, gameDto.getState());
		assertEquals(TEXAS_HOLDEM, gameDto.getSetting().getType());
		assertEquals(Integer.valueOf(3), gameDto.getSetting().getWinsRequired());
		assertNull(gameDto.getActiveDate());
	}

	@Test
	public void testTransformGameWithPlayers() {
		final Game game = Game.builder()
				.withCode("ABC123")
				.withState(CREATED)
				.withSetting(
						GameSetting.builder()
								.withGameType(TEXAS_HOLDEM)
								.withWinsRequired(3)
								.build()
				)
				.build();

		final Player player = Player.builder()
				.withGame(game)
				.withWinCounter(0)
				.withName("Bob")
				.build();

		game.getPlayers().add(player);

		final GameDto gameDto = GameTransformer.buildGameDtoWithPlayersAndHand(game);

		assertEquals("ABC123", gameDto.getCode());
		assertEquals(TEXAS_HOLDEM, gameDto.getSetting().getType());
		assertEquals(Integer.valueOf(3), gameDto.getSetting().getWinsRequired());
		assertNull(gameDto.getActiveDate());
		assertNull(gameDto.getCurrentHand());
		assertEquals("Bob", gameDto.getPlayers().get(0).getName());
		assertEquals(0, gameDto.getPlayers().get(0).getWinCounter());
	}
}
