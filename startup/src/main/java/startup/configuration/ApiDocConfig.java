package startup.configuration;

import java.util.Collections;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class ApiDocConfig {

    public ApiInfo getApiInfo() {
        return new ApiInfo("Startup Service",
                "The startup API.",
                "1.0.0",
                StringUtils.EMPTY,
                new Contact("Startup", "https://www.startup.com", null), null, null, Collections.EMPTY_LIST);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("startup.controller"))
                .paths(PathSelectors.any()).build()
                .apiInfo(this.getApiInfo())
                .genericModelSubstitutes(Optional.class);
    }

}
