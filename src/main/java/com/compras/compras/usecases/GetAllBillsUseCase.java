package com.compras.compras.usecases;

import com.compras.compras.collections.Factura;
import com.compras.compras.model.FacturaDTO;
import com.compras.compras.repositories.FacturaRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class GetAllBillsUseCase implements Supplier<Flux<FacturaDTO>> {
    FacturaRepository facturaRepository;
    MapperUtils mapperUtils;

    public GetAllBillsUseCase(FacturaRepository facturaRepository, MapperUtils mapperUtils) {
        this.facturaRepository = facturaRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
  public Flux<FacturaDTO> get() {
      return facturaRepository.findAll()
              .map(mapperUtils.mapEntityToProducto());
  }
}
