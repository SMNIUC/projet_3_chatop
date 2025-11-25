package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.model.requestDto.RentalRequestDto;
import com.openclassrooms.chatop.repositories.RentalRepository;
import com.openclassrooms.chatop.utils.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final FileStorageService fileStorageService;

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental findRentalByRentalId(Integer id) {
        return rentalRepository.findRentalByRentalId(id);
    }

    @Transactional
    public void createRental(RentalRequestDto r, User owner) {
        Rental rental = new Rental();
        rental.setRentalName(r.getName());
        rental.setRentalSurface(r.getSurface());
        rental.setRentalPrice(r.getPrice());
        rental.setRentalPicture(fileStorageService.saveFile(r.getPicture()));
        rental.setRentalDescription(r.getDescription());
        rental.setOwner(owner);
        rental.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        rental.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        rentalRepository.save(rental);
    }

    @Transactional
    public void updateRental(Integer id, RentalRequestDto r) {
        Rental rentalToUpdate = rentalRepository.findRentalByRentalId(id);
        rentalToUpdate.setRentalName(r.getName());
        rentalToUpdate.setRentalSurface(r.getSurface());
        rentalToUpdate.setRentalPrice(r.getPrice());
        rentalToUpdate.setRentalDescription(r.getDescription());
        rentalToUpdate.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        rentalRepository.save(rentalToUpdate);
    }
}
