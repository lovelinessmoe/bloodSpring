package vip.ashes.blood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author loveliness
 */
@EnableOpenApi
@SpringBootApplication
public class BloodApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloodApplication.class, args);
    }

}
