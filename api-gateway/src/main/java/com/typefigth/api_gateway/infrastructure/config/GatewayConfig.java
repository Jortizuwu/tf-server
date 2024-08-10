package com.typefigth.api_gateway.infrastructure.config;

import com.typefigth.api_gateway.infrastructure.adapters.CustomResponseFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private CustomResponseFilter customResponseFilter;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("USER-SERVICE", r -> r.path("/api/v1/users/**")
                        .filters(f -> f.filter(customResponseFilter.apply(this.customResponseFilter.newConfig())))
                        .uri("lb://USER-SERVICE"))
                .build();
    }
}