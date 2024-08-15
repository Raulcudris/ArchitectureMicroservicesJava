package com.shoppingmakiia.CustomerServicesApplication.Service;
import com.shoppingmakiia.CustomerServicesApplication.Entity.Product;
import com.shoppingmakiia.CustomerServicesApplication.Repository.ProductRepository;
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

    public Product getProductById(Long id){
        Product productId = productRepository.findByProductId(id);
        return  productId;
    }

    public Product save(Product product){
        Product productNew = productRepository.save(product);
        return productNew;
    }

}
