package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.model.requestDto.UserRequestDto;
import com.openclassrooms.chatop.model.responseDto.UserResponseDto;
import com.openclassrooms.chatop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public User getByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User findUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
    }

    public UserResponseDto getUserResponseDto(Integer userId) {
        User user = findUserById(userId);
        return new UserResponseDto(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }

    @Transactional
    public void register(UserRequestDto r) {
        User user = new User();
        user.setEmail(r.getEmail());
        user.setName(r.getName());
        user.setPassword(encoder.encode(r.getPassword()));
        user.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
    }
}
