package com.compras.compras.usecases;

import com.compras.compras.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
@Validated
public class UpdateProductUseCase implements UpdateProductService {
    @Autowired
    WebClient webClient;
    @Override
    public Mono<Producto> updateById(String id, Integer quantity) {
        return webClient.patch()
                .uri("/updateInventary/{id}/{quantity}", id,quantity)
                .retrieve()
                .bodyToMono(Producto.class);
    }
}
