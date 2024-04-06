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
import startup.dto.GenericResponseDto;
import startup.exception.GameNotFoundException;
import startup.service.PlayerService;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/player")
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
            response = GenericResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "New player joined game successfully", response = GenericResponseDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "The request was invalid."),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.")
    })
    @PostMapping(path = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object joinGame(@PathVariable final String code, @RequestParam final String name,
                                         final HttpServletResponse response) {
        try {
            final PlayerDto createdPlayer = this.playerService.joinGame(code, name);

            return GenericResponseDto.builder()
                    .withSuccess(true)
                    .withEntity(createdPlayer.getGuid())
                    .withMessage("New player successfully joined the game")
                    .build();
        } catch (final GameNotFoundException e) {
            response.setStatus(HttpStatus.SC_NOT_FOUND);

            return GenericResponseDto.builder()
                    .withSuccess(false)
                    .withMessage("Game was not found")
                    .build();
        }
    }

    @ApiOperation(
            value = "Makes a move",
            httpMethod = "POST",
            response = GenericResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Made the move successfully", response = GenericResponseDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "The request was invalid."),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.")
    })
    @PostMapping(value = "/start", produces =  MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object sow(@RequestParam final String playerId) {
        final GameDto game = this.playerService.start(playerId);

        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + game.getCode(), game);

        return GenericResponseDto.builder()
                .withSuccess(true)
                .withEntity(game)
                .withMessage("Player successfully made a move")
                .build();
    }

}