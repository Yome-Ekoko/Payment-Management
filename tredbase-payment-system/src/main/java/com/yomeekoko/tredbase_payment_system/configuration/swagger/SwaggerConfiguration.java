package com.yomeekoko.tredbase_payment_system.configuration.swagger;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;

@Configuration
public class SwaggerConfiguration {
    @PreAuthorize("permitAll")
    @Bean
    public OpenAPI customOpenAPI(@Value("0.0.1-SNAPSHOT")  String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("Tredbase Family Payment System")
                        .description("API documentation for Tredbase Family Payment System")
                        .version(appVersion));
    }
}