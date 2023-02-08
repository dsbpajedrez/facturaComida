package com.compras.compras.usecases;

import com.compras.compras.model.FacturaDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveFactura {
    Mono<String> apply(@Valid FacturaDTO facturaDTO);
}
