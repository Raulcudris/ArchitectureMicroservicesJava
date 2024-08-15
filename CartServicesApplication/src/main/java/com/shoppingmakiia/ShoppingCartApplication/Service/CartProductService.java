package com.shoppingmakiia.ShoppingCartApplication.Service;
import com.shoppingmakiia.ShoppingCartApplication.Entity.CartProduct;
import com.shoppingmakiia.ShoppingCartApplication.Repository.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartProductService {

    @Autowired
    CartProductRepository cartProductRepository;

    public List<CartProduct> getAll(){
        return  cartProductRepository.findAll();
    }

    public CartProduct getCartProductById(Long id){
        return cartProductRepository.findByCarProductId(id);
    }

    public CartProduct save(CartProduct cartProduct){
        CartProduct productNew = cartProductRepository.save(cartProduct);
        return productNew;
    }
}
