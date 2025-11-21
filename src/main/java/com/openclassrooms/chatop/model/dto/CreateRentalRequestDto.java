package com.openclassrooms.chatop.model.dto;

import lombok.Data;

@Data
public class CreateRentalRequestDto {
    private String name;
    private Double surface;
    private Double price;
    private String picture;
    private String description;
}
