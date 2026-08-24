package com.app.ecom.service;

import com.app.ecom.model.*;
import com.app.ecom.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Cart getCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found")));
                    return cartRepository.save(cart);
                });
    }

    public void addToCart(Long userId, Long productId, Integer qty) {
        Cart cart = getCart(userId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem item = new CartItem();
        item.setProduct(product);
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