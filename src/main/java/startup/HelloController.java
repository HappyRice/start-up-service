package startup;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;

@RestController
@RequestMapping("/test")
public class HelloController {

    @RequestMapping(value = "/even", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    String hello(@RequestParam int value) {
        return format("Value is an even number: %s", value % 2 == 0);
    }

}