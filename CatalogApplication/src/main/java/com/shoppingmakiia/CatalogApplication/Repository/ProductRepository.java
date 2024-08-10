package com.shoppingmakiia.CatalogApplication.Repository;

import com.shoppingmakiia.CatalogApplication.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
