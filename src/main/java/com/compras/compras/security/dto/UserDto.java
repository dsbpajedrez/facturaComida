package com.compras.compras.security.dto;

import com.compras.compras.security.enums.RolesEnum;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class UserDto {
    @Id
    private String id;
    @NotBlank(message = "Username is mandatory")
    private String userName;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email incorrect")
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
    List<RolesEnum> roles;

    public UserDto() {
    }

    public UserDto(String id, String userName, String email, String password, List<RolesEnum> roles) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RolesEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesEnum> roles) {
        this.roles = roles;
    }
}
