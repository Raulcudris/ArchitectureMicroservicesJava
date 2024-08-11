package com.shoppingmakiia.ShoppingCartApplication.Controller;
import com.shoppingmakiia.ShoppingCartApplication.Entity.Cart;
import com.shoppingmakiia.ShoppingCartApplication.Entity.CartProduct;
import com.shoppingmakiia.ShoppingCartApplication.Service.CartProductService;
import com.shoppingmakiia.ShoppingCartApplication.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    CartProductService cartProductService;

    @GetMapping("getall")
    public ResponseEntity<List<Cart>> getAll(){
        List<Cart> carts = cartService.getAll();
        if(carts.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getById(@PathVariable("id") Long id){
        Cart cartById = cartService.getCartById(id);
        if(cartById == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartById);
    }

    @PostMapping("create")
    public ResponseEntity<Cart> save(@RequestBody Cart cart){
        Cart cartNew = cartService.save(cart);
        return ResponseEntity.ok(cartNew);
    }

   @GetMapping("/cars-products")
    public ResponseEntity<CartProduct> getCartProducts(@PathVariable("id") Long id){
        CartProduct cartProductById = cartProductService.getCartProductById(id);
        if(cartProductById == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartProductById);
    }

}
