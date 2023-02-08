package com.compras.compras.routers;

import com.compras.compras.model.Producto;
import com.compras.compras.usecases.FindByNameUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class FacturasRouter {
    @Autowired
    WebClient webClient;
    @Bean
    public RouterFunction<ServerResponse> findByName(FindByNameUseCase findByNameUseCase) {
        return route(
                GET("/seekByName/{name}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(findByNameUseCase
                                .findByName(request.pathVariable("name")),Producto[].class
                        ))
        );
    }
}
