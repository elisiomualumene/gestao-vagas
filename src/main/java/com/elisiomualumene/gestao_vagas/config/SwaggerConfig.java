package com.elisiomualumene.gestao_vagas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(
                new Info().title("Gestão de Vagas").description("API Responsavél pela Gestão de Vagas").version("1")
                        .contact(new Contact().email("elisiomualumene@gmail.com").name("Elisio Mualumene"))
                        .license(new License().name("MIT")))
                .schemaRequirement("jwt_auth", securityScheme());
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme().name("jwt_auth").type(SecurityScheme.Type.HTTP).scheme("bearer")
                .bearerFormat("JWT");
    }
}
