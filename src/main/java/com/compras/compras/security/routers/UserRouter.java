package com.compras.compras.security.routers;

import com.compras.compras.model.FacturaDTO;
import com.compras.compras.security.dto.UserDto;
import com.compras.compras.security.usecase.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.naming.directory.AttributeInUseException;
import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
@Configuration
public class UserRouter {
    @Bean
    public RouterFunction<ServerResponse> createUser(UserUseCase userUseCase) {
        Function<UserDto, Mono<ServerResponse>> executor = userDTO ->  userUseCase.apply(userDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));
        return route(
                POST("/createUser").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(UserDto.class).flatMap(executor)
        );
    }
}
