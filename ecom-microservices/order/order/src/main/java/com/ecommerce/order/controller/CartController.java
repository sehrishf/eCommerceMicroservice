package com.ecommerce.order.controller;

import com.ecommerce.order.model.Cart;
import com.ecommerce.order.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PostMapping("/{userId}/add/{productId}")
    public ResponseEntity<String> addToCart(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestParam Integer qty) {

        cartService.addToCart(userId, productId, qty);
        return ResponseEntity.ok("Item added to cart");
    }

    @DeleteMapping("/{userId}/remove/{itemId}")
    public ResponseEntity<String> removeFromCart(
            @PathVariable Long userId,
            @PathVariable Long itemId) {

        cartService.removeFromCart(userId, itemId);
        return ResponseEntity.ok("Item removed");
    }
}
