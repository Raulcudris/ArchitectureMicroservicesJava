package com.shoppingmakiia.CatalogApplication.Controllers;
import com.shoppingmakiia.CatalogApplication.Dto.ProductDto;
import com.shoppingmakiia.CatalogApplication.Entity.Product;
import com.shoppingmakiia.CatalogApplication.Repository.ProductRepository;
import com.shoppingmakiia.CatalogApplication.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("getall")
    public ResponseEntity<List<Product>> getAll(){
        List<Product> products = productService.getAll();
        if(products.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") Long id){
        Product product = productService.getBikeById(id);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }


    @PostMapping("create")
    public ResponseEntity<Product> save(@RequestBody Product product){
        Product productNew = productService.save(product);
        return ResponseEntity.ok(productNew);
    }

}
