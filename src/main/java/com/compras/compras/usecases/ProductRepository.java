package com.compras.compras.usecases;

import com.compras.compras.model.Producto;
import reactor.core.publisher.Mono;

public interface ProductRepository {
    public Mono<Producto[]> findByName(String name);
    //public Mono<Producto> findById(String id);
}
