package com.ecommerce.order.services;

import com.ecommerce.order.model.Cart;
import com.ecommerce.order.model.CartItem;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.model.OrderItem;
import com.ecommerce.order.repository.OrderRepository;
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

//        double total = cart.getItems().stream()
//                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
//                .sum();

        double total = cart.getItems().stream()
                .mapToDouble(i -> 10.0 * i.getQuantity())   // dummy price
                .sum();


        Order order = new Order();
        order.setUserId(cart.getUserId());
        //order.setItems(cart.getItems());
        order.setTotalAmount(total);

        cart.getItems().clear(); // empty cart after order

        return orderRepository.save(order);
    }

    public Order placeOrder(Long userId) {

        // 1. Load cart
        Cart cart = cartService.getCart(userId);

        // 2. Dummy total calculation
        double total = cart.getItems().stream()
                .mapToDouble(i -> 10.0 * i.getQuantity())
                .sum();

        // 3. Create order
        Order order = new Order();
        order.setUserId(userId);        // only userId for now
        order.setTotalAmount(total);

        // 4. Convert CartItems → OrderItems
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);                 // FK: order_id
            orderItem.setProductId(cartItem.getProductId()); // only productId
            orderItem.setQuantity(cartItem.getQuantity());

            orderItems.add(orderItem);
        }

        // 5. Attach items to order
        order.setItems(orderItems);

        // 6. Clear cart
        cart.getItems().clear();

        // 7. Save order (cascade saves order items)
        return orderRepository.save(order);
    }

}
