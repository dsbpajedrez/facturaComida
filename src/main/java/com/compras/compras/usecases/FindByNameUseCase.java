package com.compras.compras.usecases;

import com.compras.compras.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Validated
public class FindByNameUseCase implements  ProductRepository {
    @Autowired
    WebClient webClient;
    @Override
    public Mono<Producto[]> findByName(String name) {
        return webClient.get()
                .uri("/seekByName/{name}", name)
                .retrieve()
                .bodyToMono(Producto[].class);
    }




}

