package com.compras.compras.usecases;

import com.compras.compras.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Validated
public class FindByIdUseCase implements SeekById {
    @Autowired
    WebClient webClient;
    @Override
    public Mono<Producto> findById(String id) {
        return webClient.get()
                .uri("/seekById/{id}", id)
                .retrieve()
                .bodyToMono(Producto.class);
    }
}
