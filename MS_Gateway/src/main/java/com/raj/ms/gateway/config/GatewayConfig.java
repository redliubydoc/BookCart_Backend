package com.raj.ms.gateway.config;

import com.raj.ms.gateway.filter.AccessTokenAuthNGatewayFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

  private final AccessTokenAuthNGatewayFilter accessTokenAuthNGatewayFilter;

  @Autowired
  public GatewayConfig(AccessTokenAuthNGatewayFilter accessTokenAuthNGatewayFilter) {
    this.accessTokenAuthNGatewayFilter = accessTokenAuthNGatewayFilter;
  }

  @Bean
  public RouteLocator getRouteLocator(RouteLocatorBuilder builder) {

    return builder.routes()
      .route("ms-identity", r -> r
        .path("/api/ms-identity/**")
        .filters(f -> f.filter(accessTokenAuthNGatewayFilter, 1))
        .uri("lb://ms-identity")
      )
      .route("ms-product", r -> r
        .path("/api/ms-product/**", "/static/ms-product/**")
        .uri("lb://ms-product")
      )
      .route("ms-feedback", r -> r
        .path("/api/ms-feedback/**", "/static/ms-feedback/**")
        .uri("lb://ms-feedback")
      )
      .build();

  }

}
