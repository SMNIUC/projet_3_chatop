package com.openclassrooms.chatop.model.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageRequestDto {
    private Integer rental_id;
    private Integer user_id;
    private String message;
}
