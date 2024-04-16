package startup.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import startup.common.dto.HandDto;
import startup.dto.ErrorResponse;
import startup.exception.GameNotFoundException;
import startup.exception.HandNotFoundException;
import startup.exception.InvalidHandStateTransitionException;
import startup.service.HandService;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/hands")
@Api(tags = "Hand")
public class HandController {

    private final HandService handService;

    public HandController(final HandService handService) {
        this.handService = handService;
    }

    @ApiOperation(
            value = "Creates a new hand for a game",
            httpMethod = "POST",
            response = HandDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "New hand created successfully", response = HandDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Request was invalid.", response = ErrorResponse.class),
            @ApiResponse(code = HttpStatus.SC_NOT_FOUND, message = "Game was not found.", response = ErrorResponse.class),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.", response = ErrorResponse.class)
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object createHand(@RequestParam final String gameGuid, final HttpServletResponse response) {
        try {
            return this.handService.createNewHand(gameGuid);

        } catch (final GameNotFoundException e) {
            response.setStatus(HttpStatus.SC_NOT_FOUND);

            return ErrorResponse.builder()
                    .withMessage("Game was not found")
                    .build();
        } catch (final InvalidHandStateTransitionException e) {
            response.setStatus(HttpStatus.SC_CONFLICT);

            return ErrorResponse.builder()
                    .withMessage("Hand is not in the right state")
                    .build();
        }
    }

    @ApiOperation(
            value = "Creates the flop for a hand",
            httpMethod = "POST",
            response = HandDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Flop created successfully", response = HandDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Request was invalid.", response = ErrorResponse.class),
            @ApiResponse(code = HttpStatus.SC_NOT_FOUND, message = "Hand was not found.", response = ErrorResponse.class),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.", response = ErrorResponse.class)
    })
    @PostMapping(value = "/{handGuid}/flop", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object createFlop(@PathVariable final String handGuid, final HttpServletResponse response) {
        try {
            return this.handService.createFlop(handGuid);

        } catch (final HandNotFoundException e) {
            response.setStatus(HttpStatus.SC_NOT_FOUND);

            return ErrorResponse.builder()
                    .withMessage("Hand was not found")
                    .build();
        } catch (final InvalidHandStateTransitionException e) {
            response.setStatus(HttpStatus.SC_CONFLICT);

            return ErrorResponse.builder()
                    .withMessage("Hand is not in the right state")
                    .build();
        }
    }

    @ApiOperation(
            value = "Creates the turn for a hand",
            httpMethod = "POST",
            response = HandDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Turn created successfully", response = HandDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Request was invalid.", response = ErrorResponse.class),
            @ApiResponse(code = HttpStatus.SC_NOT_FOUND, message = "Hand was not found.", response = ErrorResponse.class),
            @ApiResponse(code = HttpStatus.SC_CONFLICT, message = "Hand is not in the right state.", response = ErrorResponse.class),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.", response = ErrorResponse.class)
    })
    @PostMapping(value = "/{handGuid}/turn", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object createTurn(@PathVariable final String handGuid, final HttpServletResponse response) {
        try {
            return this.handService.createTurn(handGuid);

        } catch (final HandNotFoundException e) {
            response.setStatus(HttpStatus.SC_NOT_FOUND);

            return ErrorResponse.builder()
                    .withMessage("Hand was not found")
                    .build();
        } catch (final InvalidHandStateTransitionException e) {
            response.setStatus(HttpStatus.SC_CONFLICT);

            return ErrorResponse.builder()
                    .withMessage("Hand is not in the right state")
                    .build();
        }
    }

    @ApiOperation(
            value = "Creates the river for a hand",
            httpMethod = "POST",
            response = HandDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "River created successfully", response = HandDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Request was invalid.", response = ErrorResponse.class),
            @ApiResponse(code = HttpStatus.SC_NOT_FOUND, message = "Hand was not found.", response = ErrorResponse.class),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.", response = ErrorResponse.class)
    })
    @PostMapping(value = "/{handGuid}/river", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object createRiver(@PathVariable final String handGuid, final HttpServletResponse response) {
        try {
            return this.handService.createRiver(handGuid);

        } catch (final HandNotFoundException e) {
            response.setStatus(HttpStatus.SC_NOT_FOUND);

            return ErrorResponse.builder()
                    .withMessage("Hand was not found")
                    .build();
        } catch (final InvalidHandStateTransitionException e) {
            response.setStatus(HttpStatus.SC_CONFLICT);

            return ErrorResponse.builder()
                    .withMessage("Hand is not in the right state")
                    .build();
        }
    }
}