package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.repositories.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
}
