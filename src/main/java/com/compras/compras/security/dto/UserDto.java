package com.compras.compras.security.dto;

import com.compras.compras.security.enums.RolesEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class UserDto {
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String username;
    @Getter @Setter
    private String password;



    @Getter @Setter
    private List<RolesEnum> roles;

    public UserDto() {
    }

    public UserDto(String id, String username, String password, List<RolesEnum> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
