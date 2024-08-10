package com.shoppingmakiia.ShoppingCartApplication.Repository;
import com.shoppingmakiia.ShoppingCartApplication.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
