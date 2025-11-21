package com.openclassrooms.chatop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class RentalDto {
    private Integer rentalId;
    private String rentalName;
    private Double rentalSurface;
    private Double rentalPrice;
    private String rentalPicture;
    private String rentalDescription;
    private Integer ownerId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
