package com.example.demo;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class SimpleFilter implements GatewayFilter {
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.info("BEFORE");
		exchange.getResponse().getHeaders().add("X-SpringOneTour", "Denver");
		return chain.filter(exchange)
				.then(Mono.fromRunnable(() -> {
					log.info("AFTER");
				}));
	}

}
