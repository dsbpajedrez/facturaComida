package com.compras.compras.security.rest;

import com.compras.compras.security.collections.User;
import com.compras.compras.security.dto.UserDto;
import com.compras.compras.security.repository.UserRepository;
import com.compras.compras.security.securityHandle.PBKDF2Encoder;

import com.compras.compras.security.usecase.MapperUtilsUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
@AllArgsConstructor
@RestController
public class UsersREST {
    private PBKDF2Encoder pbkdf2Encoder;
    private MapperUtilsUser mapperUtils;
    private UserRepository userRepository;
    @PostMapping("/create")
    public Mono<String> create (@RequestBody UserDto userDTO) {
        System.out.println(userRepository.existsByUsername(userDTO.getUsername()));
        return userRepository.existsByUsername(userDTO.getUsername())
                .flatMap(username -> {
                    if(username){
                        return Mono.error(new Exception("Ya existe este usuario"));
                    }else{
                        System.out.println(userDTO);
                        userDTO.setPassword(pbkdf2Encoder.encode(userDTO.getPassword()));
                        return userRepository.save(mapperUtils
                                        .mapperToUser(null).apply(userDTO))
                                .map(User::getId);
                    }
                }).onErrorReturn("Ya existe este usuario");
    }
}
