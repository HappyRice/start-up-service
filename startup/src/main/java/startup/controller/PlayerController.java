package startup.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import startup.common.dto.GameDto;
import startup.common.dto.PlayerDto;
import startup.dto.ErrorResponse;
import startup.dto.JoinGameRequest;
import startup.exception.GameNotFoundException;
import startup.service.PlayerService;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/players")
@Api(tags = "Player")
public class PlayerController {

    private final PlayerService playerService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    public PlayerController(final PlayerService playerService, final SimpMessagingTemplate simpMessagingTemplate) {
        this.playerService = playerService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @ApiOperation(
            value = "Creates a new player that joins a game",
            httpMethod = "POST",
            response = PlayerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "New player joined game successfully", response = PlayerDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "The request was invalid."),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.")
    })
    @PostMapping(path = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object joinGame(@PathVariable final String code, @RequestBody final JoinGameRequest joinGameRequest,
                                         final HttpServletResponse response) {
        try {
            return this.playerService.joinGame(code, joinGameRequest.getName());

        } catch (final GameNotFoundException e) {
            response.setStatus(HttpStatus.SC_NOT_FOUND);

            return ErrorResponse.builder()
                    .withMessage("Game was not found")
                    .build();
        }
    }

    @ApiOperation(
            value = "Test",
            httpMethod = "POST",
            response = GameDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Test successfully", response = GameDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "The request was invalid."),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.")
    })
    @PostMapping(value = "/test", produces =  MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object sow(@RequestParam final String playerId) {
        final GameDto game = this.playerService.test(playerId);

        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + game.getCode(), game);

        return game;
    }

}