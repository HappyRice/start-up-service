package startup.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import startup.common.dto.HandDto;
import startup.dto.GenericResponseDto;
import startup.exception.GameNotFoundException;
import startup.exception.HandNotFoundException;
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
            response = GenericResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "New hand created successfully", response = GenericResponseDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "The request was invalid."),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object createHand(@RequestParam final String gameGuid, final HttpServletResponse response) {
        try {
            final HandDto hand = this.handService.createNewHand(gameGuid);

            return GenericResponseDto.builder()
                    .withSuccess(true)
                    .withEntity(hand.getGuid())
                    .withMessage("New hand successfully created")
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
            value = "Creates the flop for a hand",
            response = GenericResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Flop created successfully", response = GenericResponseDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "The request was invalid."),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.")
    })
    @PostMapping(value = "/{handGuid}/flop", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object createFlop(@PathVariable final String handGuid, final HttpServletResponse response) {
        try {
            final HandDto hand = this.handService.createFlop(handGuid);

            return GenericResponseDto.builder()
                    .withSuccess(true)
                    .withEntity(hand.getGuid())
                    .withMessage("Flop successfully created")
                    .build();
        } catch (final HandNotFoundException e) {
            response.setStatus(HttpStatus.SC_NOT_FOUND);

            return GenericResponseDto.builder()
                    .withSuccess(false)
                    .withMessage("Game was not found")
                    .build();
        }
    }
}