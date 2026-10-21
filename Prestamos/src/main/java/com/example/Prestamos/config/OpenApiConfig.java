
package com.example.Prestamos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI prestamosOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de prestamos de herramientas")
                        .version("1.0.0")
                        .description(
                                "API REST para gestionar herramientas, "
                                        + "inventario y prestamos."
                        ));
    }
}
