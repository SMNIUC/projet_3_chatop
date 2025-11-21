package com.openclassrooms.chatop.repositories;

import com.openclassrooms.chatop.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    Rental findRentalByRentalId(Integer rentalId);
}
