package com.compras.compras.usecases;

import com.compras.compras.model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GetAllProductsI {
    public Mono<Producto[]> getAllProducts(String page);
}
