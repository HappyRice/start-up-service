package startup.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import startup.common.dto.GameDto;
import startup.dto.GenericResponseDto;
import startup.exception.GameNotFoundException;
import startup.service.GameService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/game")
@Api(tags = "Game")
public class GameController {

    private final GameService gameService;

    public GameController(final GameService gameService) {
        this.gameService = gameService;
    }

    @ApiOperation(
            value = "Creates a new game",
            httpMethod = "POST",
            response = GenericResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Game created successfully", response = GenericResponseDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "The request was invalid."),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object createGame() {
        final GameDto createdGame = this.gameService.createNewGame();

        return GenericResponseDto.builder()
                .withSuccess(true)
                .withEntity(createdGame)
                .withMessage("The game was successfully created")
                .build();
    }

    @ApiOperation(
            value = "Returns the list of different game types",
            httpMethod = "GET",
            response = GenericResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Game types returned successfully", response = GenericResponseDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "The request was invalid."),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.")
    })
    @GetMapping(value = "/types", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object getGameTypes() {
        final List<String> gameTypes = this.gameService.getTypes();

        return GenericResponseDto.builder()
                .withSuccess(true)
                .withEntity(gameTypes)
                .withMessage("Game types successfully returned")
                .build();
    }

    @ApiOperation(
            value = "Returns the status of the given game",
            httpMethod = "GET",
            response = GenericResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Game status returned successfully", response = GenericResponseDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "The request was invalid."),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.")
    })
    @GetMapping(value = "/{code}/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object getStatus(@PathVariable final String code, final HttpServletResponse response) {
        try {
            final GameDto game = this.gameService.getStatus(code);

            return GenericResponseDto.builder()
                    .withSuccess(true)
                    .withEntity(game)
                    .withMessage("Game status successfully returned")
                    .build();
        } catch (final GameNotFoundException e) {
            response.setStatus(HttpStatus.SC_NOT_FOUND);

            return GenericResponseDto.builder()
                    .withSuccess(false)
                    .withMessage("Game was not found")
                    .build();
        }
    }
}