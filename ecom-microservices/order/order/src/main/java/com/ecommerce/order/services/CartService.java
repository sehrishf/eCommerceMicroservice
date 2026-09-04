package com.ecommerce.order.services;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.ecommerce.order.client.ProductClient;
import com.ecommerce.order.client.dto.ProductResponse;
import com.ecommerce.order.model.Cart;
import com.ecommerce.order.model.CartItem;
import com.ecommerce.order.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductClient productClient;

    public Cart getCart(Long userId) {

        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUserId(userId);
                    return cartRepository.save(cart);
                });
    }

    public void addToCart(Long userId, Long productId, Integer qty) {

        ProductResponse product;

        try {
            product = productClient.getProductById(productId);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product not found: " + productId
            );
        }

        if (product == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product not found: " + productId
            );
        }

        Cart cart = getCart(userId);

        CartItem item = new CartItem();
        item.setProductId(productId);
        item.setQuantity(qty);
        item.setCart(cart);

        cart.getItems().add(item);

        cartRepository.save(cart);
    }


    public void removeFromCart(Long userId, Long itemId) {

        Cart cart = getCart(userId);

        cart.getItems()
                .removeIf(i -> i.getId().equals(itemId));

        cartRepository.save(cart);
    }
}