package com.example.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Clients Microservice")
                        .version("1.0.0")
                        .description("Clients Microservice API Documentation")
                        .contact(new Contact()
                                .name("Marco Nava")
                                .email("marcobdac@gmail.com")
                                .url("https://www.linkedin.com/in/marco-antonio-nava-hernandez/"))
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT"))
                );
    }
}
