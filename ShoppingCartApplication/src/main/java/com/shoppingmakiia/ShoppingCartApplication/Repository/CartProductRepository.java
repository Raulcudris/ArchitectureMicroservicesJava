package com.shoppingmakiia.ShoppingCartApplication.Repository;
import com.shoppingmakiia.ShoppingCartApplication.Entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartProductRepository  extends JpaRepository<CartProduct , Long> {
    List<CartProduct> findByCartId(Long carritoId);


}
