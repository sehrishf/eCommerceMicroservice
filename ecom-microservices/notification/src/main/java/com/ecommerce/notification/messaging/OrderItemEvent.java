package com.ecommerce.notification.messaging;

public record OrderItemEvent(
        Long productId,
        Integer quantity
) {
}
