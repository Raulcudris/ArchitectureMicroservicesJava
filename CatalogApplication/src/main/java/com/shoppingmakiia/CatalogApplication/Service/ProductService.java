package com.shoppingmakiia.CatalogApplication.Service;

import com.shoppingmakiia.CatalogApplication.Dto.ProductDto;
import com.shoppingmakiia.CatalogApplication.Entity.Product;
import com.shoppingmakiia.CatalogApplication.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product> getAll(){
        return  productRepository.findAll();
    }

    public Product getBikeById( Long id){
        return productRepository.findById(id).orElse(null);
    }

    public Product save(Product product){
        Product productNew = productRepository.save(product);
        return productNew;
    }

}
