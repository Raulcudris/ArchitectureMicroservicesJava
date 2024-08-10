package com.shoppingmakiia.CatalogApplication.Repository;
import com.shoppingmakiia.CatalogApplication.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //Product findByProductId(Long productId);

}
