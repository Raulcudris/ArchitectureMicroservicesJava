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
        return ((exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, HttpStatus.BAD_REQUEST);
            }

            String tokenHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String[] chunks = tokenHeader.split(" ");
            if (chunks.length != 2 || !chunks[0].equals("Bearer")) {
                return onError(exchange, HttpStatus.BAD_REQUEST);
            }

            return webClient.build()
                    .post()
                    .uri("http://AUTH-SERVICES/auth/validate?token=" + chunks[1])
                    .bodyValue(new RequestDto(exchange.getRequest().getPath().toString(), exchange.getRequest().getMethod().toString()))
                    .retrieve()
                    .onStatus(HttpStatus::isError, clientResponse -> {
                        // Log the error and return an appropriate Mono error
                        return Mono.error(new RuntimeException("Error al validar el token: " + clientResponse.statusCode()));
                    })
                    .bodyToMono(TokenDto.class)
                    .map(t -> {
                        t.getToken();  // Here you might want to verify or use the token
                        return exchange;
                    })
                    .flatMap(chain::filter)
                    .onErrorResume(e -> onError(exchange, HttpStatus.SERVICE_UNAVAILABLE));  // Handle the error
        });
    }

    public Mono<Void> onError(ServerWebExchange exchange, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }

    public static class Config {}
}
