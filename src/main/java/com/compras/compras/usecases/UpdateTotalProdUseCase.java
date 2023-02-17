package com.compras.compras.usecases;

import com.compras.compras.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UpdateTotalProdUseCase implements UpdateTotalProduct{
    @Autowired
    WebClient webClient;
    @Override
    public Mono<String> apply(Producto producto) {
        return webClient.put()
                .uri("/update")
                .body(BodyInserters.fromValue(producto))
                .retrieve()
                .bodyToMono(String.class);
    }
}
