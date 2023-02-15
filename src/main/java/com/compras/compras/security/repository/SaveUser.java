package com.compras.compras.security.repository;
import com.compras.compras.security.dto.UserDto;
import reactor.core.publisher.Mono;
import javax.validation.Valid;
@FunctionalInterface
public interface SaveUser {
    Mono<String> apply(@Valid UserDto userDTO);
}
