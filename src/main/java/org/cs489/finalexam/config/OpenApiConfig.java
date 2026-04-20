package org.cs489.finalexam.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI leasingManagementOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Royal Windsor Realty Leasing API")
                        .version("v1")
                        .description("Web API documentation for apartments, leases, tenants, and revenue endpoints.")
                        .contact(new Contact()
                                .name("Royal Windsor Realty, LLC")
                                .email("support@royalwindsorrealty.example")));
    }
}

