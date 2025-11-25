package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.model.responseDto.UserResponseDto;
import com.openclassrooms.chatop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "User Controller", description = "CRUD operations for Users")
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get a user by their id")
    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable("id") Integer id) {
        return userService.getUserResponseDto(id);
    }
}
