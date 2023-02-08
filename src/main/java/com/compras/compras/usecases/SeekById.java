package com.compras.compras.usecases;

import com.compras.compras.model.Producto;
import reactor.core.publisher.Mono;

public interface SeekById {
    public Mono<Producto> findById(String id);
}
