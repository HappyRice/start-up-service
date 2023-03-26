package startup;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.String.format;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class HelloController {

    @RequestMapping(value = "/even", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    String isEvenNumber(@RequestParam String value) {
        return format("Value is an even number: %s", Integer.parseInt(value) % 2 == 0);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    String getUsers() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream("/users.json");

        StringWriter writer = new StringWriter();

        if (inputStream != null) {
            IOUtils.copy(inputStream, writer, "UTF-8");
        }

        return writer.toString();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    String getUser(@PathVariable String id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = this.getClass().getResourceAsStream("/users.json");
        List<Object> users = mapper.readValue(is, new TypeReference<List<Object>>(){});

        return mapper.writeValueAsString(users.get(Integer.parseInt(id) - 1));
    }

    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path).toAbsolutePath());
        return new String(encoded, encoding);
    }

}