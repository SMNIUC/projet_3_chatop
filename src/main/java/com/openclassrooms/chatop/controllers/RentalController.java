package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.model.dto.CreateRentalRequestDto;
import com.openclassrooms.chatop.model.dto.RentalDto;
import com.openclassrooms.chatop.services.RentalService;
import com.openclassrooms.chatop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;
    private final UserService userService;

    @Operation(summary = "Get all rentals")
    @GetMapping("/rentals")
    public List<RentalDto> getRentals() {
        return rentalService.getAllRentals();
    }

    @Operation(summary = "Get a rental by its id")
    @GetMapping("/rentals/{id}")
    public RentalDto getRentalById(@PathVariable("id") Integer id) {
        return rentalService.getRentalById(id);
    }

    @Operation(summary = "Create a new rental")
    @PostMapping("/rentals")
    public ResponseEntity<Map<String, String>> createRental(@RequestBody CreateRentalRequestDto request,
                                                            @AuthenticationPrincipal Jwt jwt) {
        User owner = userService.getByEmail(jwt.getSubject());
        rentalService.createRental(request, owner);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Rental created !");

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update a rental")
    @PutMapping("/rental/{id}")
    public ResponseEntity<Map<String, String>> updateRental(@PathVariable("id") Integer id,
                                                            @RequestBody CreateRentalRequestDto request) {
        rentalService.updateRental(id, request);
        Map<String, String> response = new HashMap<>();
        //TODO message sent?
        response.put("message", "Rental updated !");
        return ResponseEntity.ok(response);
    }

    // TODO delete route?
//    @DeleteMapping()
}
