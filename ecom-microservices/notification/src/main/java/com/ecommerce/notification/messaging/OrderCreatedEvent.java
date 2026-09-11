package com.ecommerce.notification.messaging;

import java.math.BigDecimal;
import java.util.List;

public record OrderCreatedEvent(
        Long orderId,
        Long userId,
        BigDecimal totalAmount,
        List<OrderItemEvent> items
) {
}
