package com.ecommerce.order.config;

import com.ecommerce.order.client.ProductClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ProductClientConfig {

    @Bean
    @Primary
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    @LoadBalanced
    public RestClient.Builder loadBalancedRestClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    public ProductClient productClient(
            @LoadBalanced RestClient.Builder restClientBuilder) {

        RestClient restClient = restClientBuilder
                .baseUrl("http://PRODUCT-SERVICE")
                .build();

        RestClientAdapter adapter =
                RestClientAdapter.create(restClient);

        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory
                        .builderFor(adapter)
                        .build();

        return factory.createClient(ProductClient.class);
    }
}