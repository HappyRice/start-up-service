package startup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import startup.common.dto.UserDto;
import startup.service.UserService;

import java.io.IOException;

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
            value = "Returns all users along with info",
            response = UserDto.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Users returned successfully", response = UserDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "The request was invalid."),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.")
    })
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object getUsers() throws IOException {
        return MAPPER.writeValueAsString(userService.getAllUsers());
    }

    @ApiOperation(
            tags = "Startup",
            value = "Returns info of the given user",
            response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "User info successfully returned", response = UserDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "The request was invalid."),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "An internal server error occurred.")
    })
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object getUser(@PathVariable String id) throws IOException {
        return MAPPER.writeValueAsString(userService.getUserDtoById(Integer.parseInt(id)));
    }

}