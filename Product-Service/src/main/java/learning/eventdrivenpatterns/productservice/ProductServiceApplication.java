package learning.eventdrivenpatterns.productservice;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

@EnableEurekaClient
@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(ProductServiceApplication.class, args);
        ctx.getBean("CommandGateway", CommandGateway.class);
    }

}
