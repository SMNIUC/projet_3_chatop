package com.openclassrooms.chatop.model.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RentalsListResponseDto {
    private List<RentalResponseDto> rentals;
}
