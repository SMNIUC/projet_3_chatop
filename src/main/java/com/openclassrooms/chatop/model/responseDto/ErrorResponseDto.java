package com.openclassrooms.chatop.model.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private int status;
    private String message;
    private long timestamp;
}
