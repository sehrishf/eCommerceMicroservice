package com.app.ecom.service;

import com.app.ecom.model.Cart;
import com.app.ecom.model.CartItem;
import com.app.ecom.model.Order;
import com.app.ecom.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartService cartService;
    private final OrderRepository orderRepository;

    public Order placeOrderOld(Long userId) {
        Cart cart = cartService.getCart(userId);

        double total = cart.getItems().stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum();

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setItems(cart.getItems());
        order.setTotalAmount(total);

        cart.getItems().clear(); // empty cart after order

        return orderRepository.save(order);
    }

    public Order placeOrder(Long userId) {
        Cart cart = cartService.getCart(userId);

        double total = cart.getItems().stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum();

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setTotalAmount(total);

        // FIX: create a NEW list of order items
        List<CartItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            CartItem orderItem = new CartItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            //orderItem..setOrder(order); // important for JPA
            orderItems.add(orderItem);
        }

        order.setItems(orderItems);

        // empty cart after order
        cart.getItems().clear();

        return orderRepository.save(order);
    }

}
