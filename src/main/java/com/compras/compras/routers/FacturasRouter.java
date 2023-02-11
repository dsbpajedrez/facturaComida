package com.compras.compras.routers;

import com.compras.compras.model.FacturaDTO;
import com.compras.compras.model.Producto;
import com.compras.compras.usecases.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.resourceLookupFunction;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class FacturasRouter {
    @Autowired
    WebClient webClient;

    @Bean
    public RouterFunction<ServerResponse> getAllProducts(GetAllProductsUseCase getAllProductsUseCase) {
        return route(
                GET("/getAll/{page}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllProductsUseCase
                                .getAllProducts(request.pathVariable("page")),Producto[].class
                        ))
        );
    }
    @Bean
    public RouterFunction<ServerResponse> create(CreateFacturaUseCase createFacturaUseCase){
        Function<FacturaDTO, Mono<ServerResponse>> executor = facturaDTO ->  createFacturaUseCase.apply(facturaDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));
        return route(
                POST("/createBill").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(FacturaDTO.class).flatMap(executor)
        );

    }
    @Bean
    public RouterFunction<ServerResponse> findById(FindByNameUseCase findByNameUseCase) {
        return route(
                GET("/seekByName/{name}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(findByNameUseCase
                                .findByName(request.pathVariable("name")),Producto[].class
                        ))
        );
    }
    @Bean
    public RouterFunction<ServerResponse> seekById(FindByIdUseCase findByIdUseCase) {
        return route(
                GET("/seekById/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(findByIdUseCase
                                .findById(request.pathVariable("id")),Producto.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> updateInventary(UpdateProductUseCase updateProductUseCase) {
        return route(PATCH("/updateInventary/{id}/{quantity}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse
                        .accepted()
                        .body(BodyInserters
                                .fromPublisher(updateProductUseCase
                                                .updateById(request.pathVariable("id"),
                                                        Integer.valueOf(request.pathVariable("quantity"))),
                                        Producto.class)));
    }


}
