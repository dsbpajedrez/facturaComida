package com.compras.compras.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (CorsUtils.isCorsRequest(exchange.getRequest())) {
            exchange.getResponse()
                    .getHeaders()
                    .add("Access-Control-Allow-Origin", "*");
            exchange.getResponse()
                    .getHeaders()
                    .add("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
            exchange.getResponse()
                    .getHeaders()
                    .add("Access-Control-Allow-Headers", "authorization, content-type");
            exchange.getResponse()
                    .getHeaders()
                    .add("Access-Control-Max-Age", "3600");
            if (exchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
                exchange.getResponse().setStatusCode(HttpStatus.OK);
                return Mono.empty();
            }
        }
        return chain.filter(exchange);
    }
}

