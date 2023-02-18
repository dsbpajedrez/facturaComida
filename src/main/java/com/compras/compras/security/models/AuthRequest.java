package com.compras.compras.security.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class  AuthRequest {

    private String userName;

    private String password;

}
