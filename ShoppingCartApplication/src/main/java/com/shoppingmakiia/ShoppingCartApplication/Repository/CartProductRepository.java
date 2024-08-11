package com.shoppingmakiia.ShoppingCartApplication.Repository;
import com.shoppingmakiia.ShoppingCartApplication.Entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CartProductRepository  extends JpaRepository<CartProduct , Long> {
    //CartProduct findByCarProductId(Long carritoId);
}
