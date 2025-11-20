package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.model.AuthResponse;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.model.dto.LoginRequestDto;
import com.openclassrooms.chatop.model.dto.UserDto;
import com.openclassrooms.chatop.model.dto.UserMeDto;
import com.openclassrooms.chatop.services.JWTService;
import com.openclassrooms.chatop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Tag(name = "Authentication")
@RequestMapping()
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTService jwtService;

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody UserDto userDto) {
        userService.register(userDto);
        String token = jwtService.generateToken(userDto.getEmail());

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @Operation(summary = "Login a user")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequestDto request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        String token = jwtService.generateToken(request.getEmail());

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @Operation(summary = "Get the authenticated user information")
    @GetMapping("/me")
    public UserMeDto me(@AuthenticationPrincipal Jwt jwt) {
        User user = userService.getByEmail(jwt.getSubject());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return new UserMeDto(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
