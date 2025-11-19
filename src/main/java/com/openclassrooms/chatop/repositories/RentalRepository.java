package com.openclassrooms.chatop.repositories;

import com.openclassrooms.chatop.model.Rental;
import org.springframework.data.repository.CrudRepository;

public interface RentalRepository extends CrudRepository<Rental, Integer> {
}
