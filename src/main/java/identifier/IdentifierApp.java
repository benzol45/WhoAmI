package identifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*@OpenAPIDefinition(
        info = @Info(
                title = "Identifier API",
                version = "1.0",
                description = "API documentation"
        )
)*/
@SpringBootApplication
public class IdentifierApp {
    public static void main(String[] args) {
        SpringApplication.run(IdentifierApp.class, args);

    }
}
