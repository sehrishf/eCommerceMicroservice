package com.ecommerce.order.client;

import com.ecommerce.order.client.dto.UserResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface UserClient {

    @GetExchange("/api/user/{id}")
    UserResponse getUserById(@PathVariable Long id);
}