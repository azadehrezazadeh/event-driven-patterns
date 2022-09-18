package learning.eventdrivenpatterns.productservice.controller;

import learning.eventdrivenpatterns.productservice.command.CreateProductCommand;
import learning.eventdrivenpatterns.productservice.dto.CreateProductDto;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {


    private final Environment env;
    private final CommandGateway commandGateway;

    @Autowired
    public ProductController(Environment env, CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
        this.env =env;

    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping
    public String createProduct(@RequestBody CreateProductDto createProductDto) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(createProductDto.getPrice())
                .name(createProductDto.getName())
                .id(UUID.randomUUID().toString())
                .build();

        String returnValue="";
        try {
            returnValue = commandGateway.sendAndWait(createProductCommand);
        } catch (Exception ex) {
            returnValue = ex.getLocalizedMessage();
        }
        return returnValue;
    }
}
