package com.compras.compras.usecases;

import com.compras.compras.model.Producto;
import reactor.core.publisher.Mono;

public interface PatchState {
    public Mono<Producto> apply(String id);
}
