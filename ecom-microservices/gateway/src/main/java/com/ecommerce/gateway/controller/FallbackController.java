package com.ecommerce.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class FallbackController {

    @GetMapping("/fallback/user")
    public Mono<Map<String, Object>> userFallback(ServerWebExchange exchange) {

        exchange.getResponse().setStatusCode(
                HttpStatus.SERVICE_UNAVAILABLE
        );

        return Mono.just(Map.of(
                "status", 503,
                "message", "User service is currently unavailable",
                "service", "user-service"
        ));
    }

    @GetMapping("/fallback/product")
    public Mono<Map<String, Object>> productFallback(
            ServerWebExchange exchange) {

        exchange.getResponse().setStatusCode(
                HttpStatus.SERVICE_UNAVAILABLE
        );

        return Mono.just(Map.of(
                "status", 503,
                "message", "Product service is currently unavailable",
                "service", "product-service"
        ));
    }

    @RequestMapping("/fallback/order")
    public Mono<ResponseEntity<Map<String, Object>>> orderFallback() {

        return Mono.just(
                ResponseEntity
                        .status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body(Map.of(
                                "status", 503,
                                "message", "Order service is currently unavailable",
                                "service", "order-service"
                        ))
        );
    }
}