package com.example.healthcare_system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Healthcare System API")
                        .version("1.0")
                        .description("API documentation for the Healthcare System")
                        .contact(new Contact()
                                .name("Your Name")
                                .url("http://example.com")
                                .email("your.email@example.com")));
    }
}
