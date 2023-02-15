package com.compras.compras.security.repository;

import com.compras.compras.security.collections.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String> {
    @Override
    Mono<User> findById(String s);

    Mono<Boolean> existsByUserName(String username);
    Mono<User> findByUserName(String username);
    Mono<Boolean> existsByEmail(String email);
}
