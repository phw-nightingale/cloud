package xyz.frt.serverproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaClient
@SpringBootApplication
public class ServerProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerProductApplication.class, args);
    }

    @GetMapping("/products/name/{name}")
    public String getProducts(@PathVariable String name) {
        return name;
    }

}
