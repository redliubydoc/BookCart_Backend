package com.raj.ms.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class AccessTokenAuthNGatewayFilter implements GatewayFilter {

  public AccessTokenAuthNGatewayFilter() {
    System.out.println("Hola");
  }

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

    System.out.println("Hola :: Filtered");
    return chain.filter(exchange);

  }

}
