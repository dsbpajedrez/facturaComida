package com.compras.compras.security.repository;

import com.compras.compras.security.collections.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String> {
    @Override
    Mono<User> findById(String s);


    Mono<Boolean> existsByUsername(String userName);
    Mono<User> findByUsername(String userName);
}
