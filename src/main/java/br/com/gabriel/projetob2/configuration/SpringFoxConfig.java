package br.com.gabriel.projetob2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

    // http://localhost:8080/swagger-ui/index.html
    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2) //
                .select() //
                .apis(RequestHandlerSelectors.basePackage("br.com.gabriel.projetob2")) //
                .paths(PathSelectors.any()) //
                .build();
    }
}
