package com.shoppingmakiia.CustomerServicesApplication.Controllers;
import com.shoppingmakiia.CustomerServicesApplication.Dto.ProductDto;
import com.shoppingmakiia.CustomerServicesApplication.Entity.Product;
import com.shoppingmakiia.CustomerServicesApplication.Service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
     @Autowired
     ProductService productService;

    @GetMapping("getall")
    public List<ProductDto> getAll(){
        return productService.getAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable("id") Long id){
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDto(product));
    }
    @PostMapping("create")
    public ResponseEntity<ProductDto> save(@RequestBody ProductDto productDto){
        Product product = convertToEntity(productDto);
        Product savedProduct = productService.save(product);
        return ResponseEntity.ok(convertToDto(savedProduct));
    }

    private ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }
    private Product convertToEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }

}
