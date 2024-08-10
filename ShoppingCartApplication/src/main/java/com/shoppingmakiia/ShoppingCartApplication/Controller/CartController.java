package com.shoppingmakiia.ShoppingCartApplication.Controller;
import com.shoppingmakiia.ShoppingCartApplication.Entity.Cart;
import com.shoppingmakiia.ShoppingCartApplication.Entity.CartProduct;
import com.shoppingmakiia.ShoppingCartApplication.Repository.CartProductRepository;
import com.shoppingmakiia.ShoppingCartApplication.Repository.CartRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {
    private CartRepository cartRepository;
    private CartProductRepository cartProductRepository;

    CartController(CartRepository cartRepository, CartProductRepository cartProductRepository) {
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
    }

    @GetMapping("/carritos")
    public List<Cart> getCarritos() {
        return cartRepository.findAll();
    }

    @GetMapping("/carrito")
    public Cart getCarrito(@RequestParam Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
    }

    @GetMapping("/carrito-productos")
    public List<CartProduct> getCarritoProductos(@RequestParam Long cartId) {
        return cartProductRepository.findByCartId(cartId);
    }
}
