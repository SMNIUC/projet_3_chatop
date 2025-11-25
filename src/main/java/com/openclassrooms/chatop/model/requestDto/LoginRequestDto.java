package com.openclassrooms.chatop.model.requestDto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
