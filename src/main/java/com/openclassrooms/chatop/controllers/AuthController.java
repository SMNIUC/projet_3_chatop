package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.model.responseDto.AuthResponseDto;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.model.requestDto.LoginRequestDto;
import com.openclassrooms.chatop.model.requestDto.UserRequestDto;
import com.openclassrooms.chatop.model.responseDto.UserResponseDto;
import com.openclassrooms.chatop.services.JWTService;
import com.openclassrooms.chatop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Authentication", description = "Authentication endpoints")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTService jwtService;

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> registerUser(@RequestBody UserRequestDto userRequestDto) {
        userService.register(userRequestDto);
        String token = jwtService.generateToken(userRequestDto.getEmail());

        return ResponseEntity.ok(new AuthResponseDto(token));
    }

    @Operation(summary = "Login a user")
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        String token = jwtService.generateToken(request.getEmail());

        return ResponseEntity.ok(new AuthResponseDto(token));
    }

    @Operation(summary = "Get the authenticated user information")
    @GetMapping("/me")
    public UserResponseDto me(@AuthenticationPrincipal Jwt jwt) {
        User user = userService.getByEmail(jwt.getSubject());

        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
