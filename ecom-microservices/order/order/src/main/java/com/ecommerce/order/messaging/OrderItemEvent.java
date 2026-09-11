package com.ecommerce.order.messaging;

public record OrderItemEvent(
        Long productId,
        Integer quantity
) {
}
