package com.openclassrooms.chatop.model.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class UserResponseDto {
    private Integer id;
    private String name;
    private String email;
    private Timestamp created_at;
    private Timestamp updated_at;
}
