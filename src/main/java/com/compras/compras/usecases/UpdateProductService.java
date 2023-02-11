package com.compras.compras.usecases;

import com.compras.compras.model.Producto;
import reactor.core.publisher.Mono;


public interface UpdateProductService {
    public Mono<Producto> updateById(String id, Integer quantity);
}
