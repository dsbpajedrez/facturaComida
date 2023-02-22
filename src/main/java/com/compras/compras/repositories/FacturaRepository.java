package com.compras.compras.repositories;

import com.compras.compras.collections.Factura;
import com.compras.compras.security.collections.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FacturaRepository extends ReactiveCrudRepository<Factura, String> {
    Flux<Factura> findByUsername(String userName);
}
