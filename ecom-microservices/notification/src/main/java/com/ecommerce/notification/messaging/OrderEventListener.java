package com.ecommerce.notification.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventListener {

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void handleOrderCreated(OrderCreatedEvent event) {

        log.info(
                "Received order created event. orderId={}, userId={}, total={}",
                event.orderId(),
                event.userId(),
                event.totalAmount()
        );

        // TODO:
        // Send email
        // Send push notification
        // Send SMS
        // etc.
    }
}
