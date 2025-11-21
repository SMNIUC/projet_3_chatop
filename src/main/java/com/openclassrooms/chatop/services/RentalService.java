package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.model.dto.CreateRentalRequestDto;
import com.openclassrooms.chatop.model.dto.RentalDto;
import com.openclassrooms.chatop.repositories.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;

    public List<RentalDto> getAllRentals() {
        return rentalRepository.findAll().stream()
                .map(r -> new RentalDto(
                        r.getRentalId(),
                        r.getRentalName(),
                        r.getRentalSurface(),
                        r.getRentalPrice(),
                        r.getRentalPicture(),
                        r.getRentalDescription(),
                        r.getOwner().getId(),
                        r.getCreatedAt(),
                        r.getUpdatedAt()
                ))
                .toList();
    }

    public RentalDto getRentalById(Integer id) {
        Rental rental = rentalRepository.findRentalByRentalId(id);
        return new RentalDto(
                rental.getRentalId(),
                rental.getRentalName(),
                rental.getRentalSurface(),
                rental.getRentalPrice(),
                rental.getRentalPicture(),
                rental.getRentalDescription(),
                rental.getOwner().getId(),
                rental.getCreatedAt(),
                rental.getUpdatedAt()
        );
    }

    public void createRental(CreateRentalRequestDto request, User owner) {
        Rental rental = new Rental();
        rental.setRentalName(request.getName());
        rental.setRentalSurface(request.getSurface());
        rental.setRentalPrice(request.getPrice());
        rental.setRentalPicture(request.getPicture());
        rental.setRentalDescription(request.getDescription());
        rental.setOwner(owner);
        rental.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        rentalRepository.save(rental);
    }

    public void updateRental(Integer id, CreateRentalRequestDto request) {
        Rental rentalToUpdate = rentalRepository.findRentalByRentalId(id);
        rentalToUpdate.setRentalName(request.getName());
        rentalToUpdate.setRentalSurface(request.getSurface());
        rentalToUpdate.setRentalPrice(request.getPrice());
        rentalToUpdate.setRentalPicture(request.getPicture());
        rentalToUpdate.setRentalDescription(request.getDescription());
        rentalToUpdate.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        rentalRepository.save(rentalToUpdate);
    }

    // TODO delete route?
//    public void deleteRental(Integer id) {
//        Rental rentalToDelete = rentalRepository.findRentalByRentalId(id);
//        rentalRepository.delete(rentalToDelete);
//    }
}
