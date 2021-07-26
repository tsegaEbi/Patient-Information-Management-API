package com.therapy.therapy.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationResponse {
    private final String jwt;
    public AuthenticationResponse(String jwt){
        this.jwt =jwt;
    }
    public String getJwt()
    {
        return jwt;
    }
}
