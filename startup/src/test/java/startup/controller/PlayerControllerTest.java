package startup.controller;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import startup.dto.JoinGameRequest;
import startup.exception.GameNotFoundException;
import startup.model.Game;
import startup.persistence.PlayerRepository;
import startup.service.GameService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static startup.common.enumeration.GameState.CREATED;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class PlayerControllerTest {

	private static final String PLAYER_PATH = "/players";

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private GameService gameService;

	@MockBean
	private PlayerRepository playerRepository;

	@Test
	public void joinGameOK() throws JSONException, GameNotFoundException {
		// Given
		final String expected = "{\n" +
				"  \"guid\": \"58733591-66b7-4621-99df-618942394f40\",\n" +
				"  \"name\": \"John\",\n" +
				"  \"winCounter\": 0\n" +
				"}";

		// Mock
		when(this.gameService.getGameByCode("ABC123")).thenReturn(Game.builder().withState(CREATED).build());

		// When
		final ResponseEntity<String> response = this.restTemplate.exchange(PLAYER_PATH + "/ABC123",
				HttpMethod.POST, new HttpEntity<>(JoinGameRequest.builder().withName("John").build()), String.class);

		// Then
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		JSONAssert.assertEquals(expected, response.getBody(), new CustomComparator(JSONCompareMode.LENIENT,
				new Customization("id", (o1, o2) -> true),
				new Customization("guid", (o1, o2) -> true)));
	}
}
