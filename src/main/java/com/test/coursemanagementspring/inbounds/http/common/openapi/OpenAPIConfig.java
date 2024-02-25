package com.test.coursemanagementspring.inbounds.http.common.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI newOpenAPI() {
        Server server = new Server();
        server.url("/");
        server.setDescription("Default");

        Contact contact = new Contact();
        contact.setEmail("matt.bert@live.fr");
        contact.setName("Matthias");

        License license = new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("CourseManagementSpring")
                .version("0.0.1")
                .contact(contact)
                .description("This is a test application to get back into Spring. It is also used to try out a bunch of stuff regarding project architecture / code structuration.")
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(List.of(server));
    }
}

