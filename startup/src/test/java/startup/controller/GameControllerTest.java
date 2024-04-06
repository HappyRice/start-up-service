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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import startup.persistence.GameRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class GameControllerTest {

	private static final String GAME_PATH = "/game";

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private GameRepository gameRepository;

	@Test
	public void getGameTypesOK() throws JSONException {
		// Given
		final String expected = "{\n" +
				"    \"success\": true,\n" +
				"    \"entity\": [\n" +
				"        \"Texas Hold'em\",\n" +
				"        \"Crazy Pineapple\"\n" +
				"    ],\n" +
				"    \"message\": \"Game types successfully returned\"\n" +
				"}";

		// When
		final ResponseEntity<String> response = this.restTemplate.exchange(GAME_PATH + "/types", HttpMethod.GET,
				null, String.class);

		// Then
		assertEquals(HttpStatus.OK, response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), true);
	}

	@Test
	public void createGameOK() throws JSONException {
		// Given
		final String expected = "{\n" +
				"    \"success\": true,\n" +
				"    \"entity\": \"c36da917-3f1f-4fa9-81f4-298573a72363\",\n" +
				"    \"message\": \"The game was successfully created\"\n" +
				"}";

		// When
		final ResponseEntity<String> response = this.restTemplate.exchange(GAME_PATH, HttpMethod.POST, null,
				String.class);

		// Then
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		JSONAssert.assertEquals(expected, response.getBody(), new CustomComparator(JSONCompareMode.LENIENT,
						new Customization("entity", (o1, o2) -> true)));
	}
}
