package startup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import startup.service.UserService;

import java.io.IOException;

import static java.lang.String.format;

@RestController
@CrossOrigin
@RequestMapping("/startup")
@Api(tags = "Startup")
public class HelloController {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    private final UserService userService;

    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(
            tags = "Startup",
            value = "Test for even number",
            notes = "Returns whether the input value is an even number.",
            response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Number processed", response = String.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "The request was invalid."),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.")
    })
    @RequestMapping(value = "/even", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object isEvenNumber(@RequestParam String value) {
        return format("Value is an even number: %s", Integer.parseInt(value) % 2 == 0);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object getUsers() throws IOException {
        return MAPPER.writeValueAsString(userService.getAllUsers());
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object getUser(@PathVariable String id) throws IOException {

        return MAPPER.writeValueAsString(userService.getUserDtoById(Integer.parseInt(id)));
    }

}