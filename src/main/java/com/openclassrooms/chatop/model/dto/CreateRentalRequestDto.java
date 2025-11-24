package com.openclassrooms.chatop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class CreateRentalRequestDto {
    private String name;
    private Double surface;
    private Double price;
    private MultipartFile picture;
    private String description;
}
