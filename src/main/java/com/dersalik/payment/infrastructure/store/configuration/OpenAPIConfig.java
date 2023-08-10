package com.dersalik.payment.infrastructure.store.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@Configuration
public class OpenAPIConfig implements ApplicationRunner{

    @Value("${salik.openapi.dev-url}")
    private String devUrl;
    @Value("${open.browser}")
    private boolean openBrowser;
//
//    @Value("${bezkoder.openapi.prod-url}")
//    private String prodUrl;

    // this function will run after the application is started
    // it will launch swagger UI in the default browser
    @Override
    public void run(ApplicationArguments args) throws URISyntaxException, IOException {
        if (openBrowser) {
            // Open Swagger UI in the default browser (Only if openBrowser is set to true)
            URI swaggerUri = new URI("http://localhost:8080/swagger-ui/index.html#/");


            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + swaggerUri);
        }
    }


    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

//        Server prodServer = new Server();
//        prodServer.setUrl(prodUrl);
//        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("mahamadsardarf@gmail.com");
        contact.setName("Mahamad Sardar");
        contact.setUrl("https://mahamad-sardar.azurewebsites.net/");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Payment API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage Payments.")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}