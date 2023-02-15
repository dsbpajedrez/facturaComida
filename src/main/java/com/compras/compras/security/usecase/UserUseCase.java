package com.compras.compras.security.usecase;

import com.compras.compras.collections.Factura;
import com.compras.compras.security.collections.User;
import com.compras.compras.security.dto.UserDto;
import com.compras.compras.security.enums.RolesEnum;
import com.compras.compras.security.repository.SaveUser;
import com.compras.compras.security.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.naming.directory.AttributeInUseException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Validated
public class UserUseCase implements SaveUser {
    private final UserRepository userRepository;
    private final MapperUtilsUser mapperUtilsUser;

    public UserUseCase(UserRepository userRepository, MapperUtilsUser mapperUtilsUser) {
        this.userRepository = userRepository;
        this.mapperUtilsUser = mapperUtilsUser;
    }

    @Override
    public Mono<String> apply(UserDto userDTO) {
        return  userRepository.existsByEmail(userDTO.getEmail())
                .flatMap(user -> {
                    if (user) {
                        return  Mono.error(new Exception("Ya existe mi rey"));
                    }
                    return userRepository
                            .save(mapperUtilsUser.mapperToUser(null).apply(userDTO))
                            .map(User::getId);
                }).onErrorReturn("Ya existe");
    }
}
