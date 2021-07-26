package com.therapy.therapy.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationRequest {
    private String userName;
    private String password;

    public AuthenticationRequest(String userName, String password){
        this.userName =userName;
        this.password=password;
    }

}
