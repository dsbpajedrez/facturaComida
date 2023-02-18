package com.compras.compras.security.usecase;

import com.compras.compras.model.FacturaDTO;
import com.compras.compras.security.collections.User;
import com.compras.compras.security.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtilsUser {
    public Function<UserDto, User> mapperToUser(String id) {
        return updateUser -> {
            var userDTO = new User();
            userDTO.setUsername(updateUser.getUsername());
            userDTO.setId(updateUser.getId());
            userDTO.setPassword(updateUser.getPassword());
            userDTO.setRoles(updateUser.getRoles());
            return userDTO;
        };
    }

    public Function<User, UserDto> mapEntityToUser() {
        return entity -> new UserDto(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getRoles()
        );
    }
}
