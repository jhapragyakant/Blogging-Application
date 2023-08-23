package com.pragyakantjha.blogging.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getInfo() {
        return new ApiInfo("Blogging Application",
                "This project is developed by Pragya",
                "1.0", "Terms of Service",
                new Contact("Pragya Kant Jha", "https://github.com/jhapragyakant/Blogging-Application", "pkjakv123@gmail.com"),
                "Liscenece of Apis",
                "Liscence URL",
                Collections.emptyList());
    }
}
