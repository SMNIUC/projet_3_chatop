package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;
}
