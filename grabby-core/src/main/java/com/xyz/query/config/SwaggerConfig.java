package com.xyz.query.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        HashSet<String> consumesAndProduces = new HashSet<String>(Arrays.asList("application/json"));
        return new Docket(DocumentationType.SWAGGER_2)
                 .directModelSubstitute(LocalDateTime.class, String.class)
                 .directModelSubstitute(LocalDate.class, String.class)
                 .directModelSubstitute(LocalTime.class, String.class)
                 .directModelSubstitute(ZonedDateTime.class, String.class)
                 .apiInfo(metadata())
                 .consumes(consumesAndProduces)
                 .produces(consumesAndProduces)
                 .select()
                 .apis(RequestHandlerSelectors.any())
                 .paths(PathSelectors.any())
                 .build();
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder().title("Grabby API").description("API for mobile apps and the Webapp")
                .version("0.0.1-beta").build();
    }
}
