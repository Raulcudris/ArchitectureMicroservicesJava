package com.shoppingmakiia.ShoppingCartApplication.Service;
import com.shoppingmakiia.ShoppingCartApplication.Entity.Cart;
import com.shoppingmakiia.ShoppingCartApplication.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    public List<Cart> getAll(){
        return  cartRepository.findAll();
    }

    public Cart getCartById( Long id){
        return cartRepository.findById(id).orElse(null);
    }

    public Cart save(Cart cart){
        Cart cartNew = cartRepository.save(cart);
        return cartNew;
    }


}
