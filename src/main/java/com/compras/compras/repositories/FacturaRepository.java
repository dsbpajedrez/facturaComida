package com.compras.compras.repositories;

import com.compras.compras.collections.Factura;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends ReactiveCrudRepository<Factura, String> {
}
