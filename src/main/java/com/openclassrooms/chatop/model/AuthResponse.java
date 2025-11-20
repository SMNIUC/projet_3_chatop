package com.openclassrooms.chatop.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponse {
    public String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
