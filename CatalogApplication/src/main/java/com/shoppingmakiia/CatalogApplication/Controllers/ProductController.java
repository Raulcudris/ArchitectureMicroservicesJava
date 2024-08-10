package com.shoppingmakiia.CatalogApplication.Controllers;
import com.shoppingmakiia.CatalogApplication.Entity.Product;
import com.shoppingmakiia.CatalogApplication.Repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProductController {
    private ProductRepository repository;

    ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/productos")
    public List<Product> getProductos() {
        return repository.findAll();
    }

    @GetMapping("/producto")
    public Product getProducto(@RequestParam Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));
    }
}
