package com.shoppingmakiia.GatewayServicesApplication.Config;

import com.shoppingmakiia.GatewayServicesApplication.Dto.RequestDto;
import com.shoppingmakiia.GatewayServicesApplication.Dto.TokenDto;
import org.springframework.stereotype.Component;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final WebClient.Builder webClient;

    public AuthFilter(WebClient.Builder webClient) {
        super(Config.class);
        this.webClient = webClient;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, HttpStatus.UNAUTHORIZED, "Missing Authorization Header");
            }

            String tokenHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
                return onError(exchange, HttpStatus.BAD_REQUEST, "Invalid Authorization Header");
            }

            String token = tokenHeader.substring(7); // Remove "Bearer " from token

            return webClient.build()
                    .post()
                    .uri("http://Auth-Services/auth/validate?token=" + token)
                    .bodyValue(new RequestDto(exchange.getRequest().getPath().toString(), exchange.getRequest().getMethod().toString()))
                    .retrieve()
                    .bodyToMono(TokenDto.class)
                    .flatMap(t -> {
                        // Token is valid, continue to the next filter
                        return chain.filter(exchange);
                    })
                    .onErrorResume(e -> onError(exchange, HttpStatus.UNAUTHORIZED, "Unauthorized Access"));
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus status, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        response.getHeaders().add("Error", message);
        return response.setComplete();
    }

    public static class Config {}
}
