package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.model.requestDto.RentalRequestDto;
import com.openclassrooms.chatop.model.responseDto.RentalResponseDto;
import com.openclassrooms.chatop.model.responseDto.RentalsListResponseDto;
import com.openclassrooms.chatop.services.RentalService;
import com.openclassrooms.chatop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "Rentals", description = "CRUD operations for Rentals")
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;
    private final UserService userService;

    @Operation(summary = "Get all rentals")
    @GetMapping("")
    public RentalsListResponseDto getRentals() {
        return new RentalsListResponseDto(rentalService.getAllRentals());
    }

    @Operation(summary = "Get a rental by its id")
    @GetMapping("/{id}")
    public RentalResponseDto getRentalById(@PathVariable("id") Integer id) {
        return rentalService.getRentalById(id);
    }

    @Operation(summary = "Get a rental image by its url")
    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("uploads/rentals/").resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Create a new rental")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> createRental(@ModelAttribute RentalRequestDto request,
                                                            @AuthenticationPrincipal Jwt jwt) {
        User owner = userService.getByEmail(jwt.getSubject());
        rentalService.createRental(request, owner);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Rental created !");

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update a rental")
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> updateRental(@PathVariable("id") Integer id,
                                                            @ModelAttribute RentalRequestDto request) {
        rentalService.updateRental(id, request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Rental updated !");

        return ResponseEntity.ok(response);
    }
}
