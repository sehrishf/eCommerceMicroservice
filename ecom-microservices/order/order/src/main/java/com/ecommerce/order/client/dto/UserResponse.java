package com.ecommerce.order.client.dto;

public record UserResponse(
        Long id,
        String username,
        String email
) {
}