package com.compras.compras.usecases;

import com.compras.compras.collections.Factura;
import com.compras.compras.model.FacturaDTO;
import com.compras.compras.repositories.FacturaRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;
@Service
@Validated
public class CreateFacturaUseCase {
    private final FacturaRepository facturaRepository;
    private final MapperUtils mapperUtils;

    public CreateFacturaUseCase(FacturaRepository facturaRepository, MapperUtils mapperUtils) {
        this.facturaRepository = facturaRepository;
        this.mapperUtils = mapperUtils;
    }


    public Mono<String> apply(FacturaDTO facturaDTO){
        return facturaRepository
                .save(mapperUtils.mapperToFactura(null).apply(facturaDTO))
                .map(Factura::getId);

    }
}
