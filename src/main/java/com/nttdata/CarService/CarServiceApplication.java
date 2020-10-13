package com.nttdata.CarService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CarServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarServiceApplication.class, args);
    }

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/car/*"))
                .apis(RequestHandlerSelectors.basePackage("com.nttdata"))
                .build()
                .apiInfo(apiDetails())
                .enableUrlTemplating(true);
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "CarService",
                "CAR Service CRUD API",
                "1.1",
                "Free to use",
                new springfox.documentation.service.Contact("Lukas", "https://www.lukasljl.de", "lukas@lukasljl.de"),
                "API License",
                "https://lukasljl.de",
                Collections.emptyList());
    }


}
