package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableHystrix
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public DiscoveryClientRouteDefinitionLocator discoveryRoutes(
			DiscoveryClient dc,
			DiscoveryLocatorProperties dlprops) {
		return new DiscoveryClientRouteDefinitionLocator(dc, dlprops);
	}



	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {

		return builder.routes().route("start", ps -> {
			return ps.path("/start")
					.uri("http://start.spring.io:80/")
					.filter(new SimpleFilter());
		})
		.route("hello", ps-> {
			return ps.path("/hello")
					.uri("lb://helloservice");
		})

		.build();
	}

	@Bean
	KeyResolver userKeyResolver() {
		return exchange -> Mono.just("habuma");
	}
}
