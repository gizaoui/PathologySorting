package fr.softwaymedical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaRepositories(enableDefaultTransactions = false)
@OpenAPIDefinition(info = @Info(title = "Pathologie sorting API", version = "1.0", description = "Pathologie sorting"))
public class SoDataProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SoDataProviderApplication.class, args);
    }
}
