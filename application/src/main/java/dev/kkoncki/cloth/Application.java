package dev.kkoncki.cloth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
        "dev.kkoncki.cloth.user.management",
        "dev.kkoncki.cloth.auth",
        "dev.kkoncki.cloth.product"
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
