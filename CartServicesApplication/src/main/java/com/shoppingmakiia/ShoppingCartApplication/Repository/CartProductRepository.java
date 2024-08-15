package com.shoppingmakiia.ShoppingCartApplication.Repository;
import com.shoppingmakiia.ShoppingCartApplication.Entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartProductRepository  extends JpaRepository<CartProduct , Long> {
    String FILTER_CARTSHOPPING_QUERY = "SELECT c FROM CartProduct c WHERE  c.id = :nroRegCartId";
    @Query(value = FILTER_CARTSHOPPING_QUERY)
    CartProduct findByCarProductId(@Param("nroRegCartId") Long nroRegCartId);
}
