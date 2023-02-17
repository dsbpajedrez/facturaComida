package com.compras.compras.usecases;

import com.compras.compras.model.Producto;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public interface UpdateTotalProduct {
    public Mono<String> apply(@Valid Producto producto);
}
