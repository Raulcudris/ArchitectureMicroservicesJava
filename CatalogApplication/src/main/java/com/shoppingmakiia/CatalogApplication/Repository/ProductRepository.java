package com.shoppingmakiia.CatalogApplication.Repository;
import com.shoppingmakiia.CatalogApplication.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product,Long > {
    String FILTER_PRODUCT_QUERY = "SELECT c FROM Product c WHERE  c.id = :nroRegProduct";
    @Query(value = FILTER_PRODUCT_QUERY)
    Product findByProductId(@Param("nroRegProduct") Long ProductId);
}
