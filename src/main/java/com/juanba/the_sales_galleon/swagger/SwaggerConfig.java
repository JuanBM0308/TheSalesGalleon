package com.juanba.the_sales_galleon.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // TODO: Base url: http://localhost:8080/swagger-ui/index.html

    @Bean
    public OpenAPI customOpenAPI(@Value("0.0.1") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("The sales galleon API")
                        .version(appVersion)
                        .description("""
                                The Sales Galleon is a product and sales management application built with Spring Boot 3.5.0 and Java 24. Its core purpose is to provide a robust platform for managing a wide catalog of products, users (with defined roles and permissions), categories, and the comprehensive handling of purchase orders and their details.
                                
                                The project incorporates key features of a modern enterprise application:
                                
                                - RESTful API: It exposes a series of endpoints for interacting with its main functionalities.
                                - Data Management: It uses Spring Data JPA for data persistence, connecting to a PostgreSQL database.
                                - Security: It implements robust security with Spring Security, JWT authentication, and a granular role and permission system (ADMINISTRATOR, CUSTOMER, VENDOR, VISITOR) to control access to different resources and operations.
                                - Lombok: It leverages Lombok to reduce boilerplate code, such as getters, setters, constructors, and more.
                                - Dockerization: The application is ready for deployment in Docker containers using a Dockerfile and docker-compose.yml, which simplifies the setup of development and production environments, including orchestration with the PostgreSQL database.
                                
                                In essence, "The Sales Galleon" simulates the core logic of an e-commerce or inventory system, focusing on modularity and the implementation of best practices in backend development with Spring
                                """));
    }
}
