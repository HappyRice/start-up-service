package startup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import startup.service.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import static java.lang.String.format;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class HelloController {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    private final UserService userService;

    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/even", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    String isEvenNumber(@RequestParam String value) {
        return format("Value is an even number: %s", Integer.parseInt(value) % 2 == 0);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    String getUsers() throws IOException {
        return MAPPER.writeValueAsString(userService.getAllUsers());
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    String getUser(@PathVariable String id) throws IOException {

        return MAPPER.writeValueAsString(userService.getUserDtoById(Integer.parseInt(id)));
    }

}