package com.shoppingmakiia.ShoppingCartApplication.Controller;
import com.shoppingmakiia.ShoppingCartApplication.Dto.CartDto;
import com.shoppingmakiia.ShoppingCartApplication.Dto.CartProductDto;
import com.shoppingmakiia.ShoppingCartApplication.Entity.Cart;
import com.shoppingmakiia.ShoppingCartApplication.Entity.CartProduct;
import com.shoppingmakiia.ShoppingCartApplication.Service.CartProductService;
import com.shoppingmakiia.ShoppingCartApplication.Service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    CartProductService cartProductService;

    @GetMapping("getall")
    public List<CartDto> getAll(){
        return cartService.getAll().stream()
                .map(this::convertToDtoCart)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getById(@PathVariable("id") Long id){
        Cart cartById = cartService.getCartById(id);
        if(cartById == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDtoCart(cartById));
    }

    @PostMapping("create")
    public ResponseEntity<CartDto> save(@RequestBody CartDto cartDto){
        Cart cart = convertToEntityCart(cartDto);
        Cart saveCart = cartService.save(cart);
        return ResponseEntity.ok(convertToDtoCart(saveCart));
    }

   @GetMapping("/cars-products")
    public ResponseEntity<CartProductDto> getCartProducts(@PathVariable("id") Long id){
        CartProduct cartProductById = cartProductService.getCartProductById(id);
        if (cartProductById == null){
            return ResponseEntity.notFound().build();
         }
        return ResponseEntity.ok(convertToDtoCartProduct(cartProductById));
    }

    private CartDto convertToDtoCart(Cart cart) {
        CartDto cartDto = new CartDto();
        BeanUtils.copyProperties(cart, cartDto);
        return cartDto;
    }
    private Cart convertToEntityCart(CartDto cartDto) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartDto, cart);
        return cart;
    }

    private CartProductDto convertToDtoCartProduct(CartProduct cartProduct) {
        CartProductDto cartProductDto = new CartProductDto();
        BeanUtils.copyProperties(cartProduct, cartProductDto);
        return cartProductDto;
    }
    private CartProduct convertToEntityCartProduct(CartProductDto cartProductDto) {
        CartProduct cartProduct = new CartProduct();
        BeanUtils.copyProperties(cartProductDto, cartProduct);
        return cartProduct;
    }

}
