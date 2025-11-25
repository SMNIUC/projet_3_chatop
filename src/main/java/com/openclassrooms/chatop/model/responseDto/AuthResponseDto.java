package com.openclassrooms.chatop.model.responseDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponseDto {
    public String token;

    public AuthResponseDto(String token) {
        this.token = token;
    }
}
