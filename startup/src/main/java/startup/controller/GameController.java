package startup.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import startup.common.dto.GameDto;
import startup.dto.ErrorResponse;
import startup.exception.GameNotFoundException;
import startup.service.GameService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/games")
@Api(tags = "Game")
public class GameController {

    private final GameService gameService;

    public GameController(final GameService gameService) {
        this.gameService = gameService;
    }

    @ApiOperation(
            value = "Creates a new game",
            httpMethod = "POST",
            response = GameDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Game created successfully", response = GameDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Request was invalid.", response = ErrorResponse.class),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.", response = ErrorResponse.class)
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public GameDto createGame() {

        return this.gameService.createNewGame();
    }

    @ApiOperation(
            value = "Returns the list of different game types",
            httpMethod = "GET",
            responseContainer = "List",
            response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Game types returned successfully", response = String.class, responseContainer = "List"),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Request was invalid.", response = ErrorResponse.class),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.", response = ErrorResponse.class)
    })
    @GetMapping(value = "/types", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getGameTypes() {

        return this.gameService.getTypes();
    }

    @ApiOperation(
            value = "Returns the status of the given game",
            httpMethod = "GET",
            response = GameDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Game status returned successfully", response = GameDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Request was invalid.", response = ErrorResponse.class),
            @ApiResponse(code = HttpStatus.SC_NOT_FOUND, message = "Game was not found.", response = ErrorResponse.class),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.", response = ErrorResponse.class)
    })
    @GetMapping(value = "/{code}/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public GameDto getStatus(@PathVariable final String code) throws GameNotFoundException {

        return this.gameService.getStatus(code);
    }
}