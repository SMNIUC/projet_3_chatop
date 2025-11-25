package com.openclassrooms.chatop.model.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class RentalRequestDto {
    private String name;
    private Double surface;
    private Double price;
    private MultipartFile picture;
    private String description;
}
