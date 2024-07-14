package controller;

import lombok.AllArgsConstructor;
import model.Product;
import service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody Product product) throws URISyntaxException {
        log.info("Добавление продукта {}", product.getName());

        long id = productService.save(product);

        return ResponseEntity
                .created(new URI("http://localhost:8080/products/" + id))
                .build();
    }

    @GetMapping("/products")
    public List<Product> getProducts(@RequestParam(value = "name", required = false) String name) {
        log.info("Поиск продуктов по имени {}", name);

        return productService.findAll(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProducts(@PathVariable long id) {
        log.info("Поиск продукта по идентификатору {}", id);

        return productService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        log.info("Обновление продукта {}", product.getId());

        productService.update(product);

        return product;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        log.info("Удаление продукта по идентификатору {}", id);

        return productService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
