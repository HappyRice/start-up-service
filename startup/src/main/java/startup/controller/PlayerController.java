package startup.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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

    public PlayerController(final PlayerService playerService) {
        this.playerService = playerService;
    }

    @ApiOperation(
            value = "Creates a new player that joins a game",
            response = GenericResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "New player joined game successfully", response = GenericResponseDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "The request was invalid."),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.")
    })
    @RequestMapping(path = "/{gameGuid}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object joinGame(@PathVariable final String gameGuid, @RequestParam final String name,
                                         final HttpServletResponse response) {
        try {
            final PlayerDto createdPlayer = this.playerService.joinGame(gameGuid, name);

            return GenericResponseDto.builder()
                    .withSuccess(true)
                    .withIdentifier(createdPlayer.getGuid())
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

}