package com.ecommerce.order.services;

import com.ecommerce.order.model.*;
import com.ecommerce.order.repository.*;
import com.ecommerce.order.model.Cart;
import com.ecommerce.order.model.CartItem;
import com.ecommerce.order.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
   // private final ProductRepository productRepository;
   // private final UserRepository userRepository;

    public Cart getCart(Long userId) {
//        return cartRepository.findByUserId(userId)
//                .orElseGet(() -> {
//                    Cart cart = new Cart();
//                    cart.setUser(userRepository.findById(userId)
//                            .orElseThrow(() -> new RuntimeException("User not found")));
//                    return cartRepository.save(cart);
//                });
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUserId(userId);
                    return cartRepository.save(cart);
                });


    }
    public void addToCart(Long userId, Long productId, Integer qty) {
        Cart cart = getCart(userId);

        CartItem item = new CartItem();

        item.setProductId(productId);
        item.setQuantity(qty);
        item.setCart(cart);         // REQUIRED

        cart.getItems().add(item);

        cartRepository.save(cart);
    }

    public void addToCartOld(Long userId, Long productId, Integer qty) {
        Cart cart = getCart(userId);

//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem item = new CartItem();
        item.setProductId(productId);
        item.setQuantity(qty);

        cart.getItems().add(item);
        cartRepository.save(cart);
    }

    public void removeFromCart(Long userId, Long itemId) {
        Cart cart = getCart(userId);
        cart.getItems().removeIf(i -> i.getId().equals(itemId));
        cartRepository.save(cart);
    }
}