package com.compras.compras.usecases;

import com.compras.compras.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Validated
public class GetAllProductsUseCase implements GetAllProductsI {
    @Autowired
    WebClient webClient;

    @Override
    public Mono<Producto[]> getAllProducts(String page) {
        return webClient.get()
                .uri("/getAll/{page}", page)
                .retrieve()
                .bodyToMono(Producto[].class);
    }


    public Mono<Producto[]> getAdminProducts(String page) {
        return webClient.get()
                .uri("/getAllProducts/{page}", page)
                .retrieve()
                .bodyToMono(Producto[].class);
    }
}
