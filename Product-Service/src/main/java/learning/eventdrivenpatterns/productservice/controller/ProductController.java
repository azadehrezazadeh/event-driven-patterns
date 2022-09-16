package learning.eventdrivenpatterns.productservice.controller;

import learning.eventdrivenpatterns.productservice.dto.CreateProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public ResponseEntity getAll()
    {
        return  ResponseEntity.ok("Hello World");
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody CreateProductDto createProductDto)
    {
        return  ResponseEntity.ok(createProductDto.getName());
    }
}
