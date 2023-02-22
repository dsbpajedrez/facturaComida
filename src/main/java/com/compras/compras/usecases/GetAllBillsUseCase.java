package com.compras.compras.usecases;

import com.compras.compras.collections.Factura;
import com.compras.compras.model.FacturaDTO;
import com.compras.compras.repositories.FacturaRepository;
import com.compras.compras.security.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

@Service
@Validated
public class GetAllBillsUseCase implements Supplier<Flux<FacturaDTO>> {
    FacturaRepository facturaRepository;
    MapperUtils mapperUtils;
    UserRepository userRepository;

    public GetAllBillsUseCase(FacturaRepository facturaRepository, MapperUtils mapperUtils, UserRepository userRepository) {
        this.facturaRepository = facturaRepository;
        this.mapperUtils = mapperUtils;
        this.userRepository = userRepository;
    }

    @Override
  public Flux<FacturaDTO> get() {
      return facturaRepository.findAll()
              .map(mapperUtils.mapEntityToProducto());
  }

  public Flux<FacturaDTO> findByUserName(String username) {
        return facturaRepository.findByUsername(username)
                .map(mapperUtils.mapEntityToProducto());
  }
}
