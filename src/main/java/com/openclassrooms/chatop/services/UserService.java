package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.model.dto.UserDto;
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

//    public List<User> getAllUsers() {
//        List<User> allUsersList = new ArrayList<>();
//        userRepository.findAll().forEach(allUsersList::add);
//
//        return allUsersList;
//    }
//
//    public User getByUsername(String username) {
//        return userRepository.findByName(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }

    public User getByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

//    public User findUserById(Integer userId) {
//        return userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
//    }
//
//    public UserDto getUserDto(Integer userId) {
//        User user = findUserById(userId);
//
//        UserDto UserDto = new UserDto();
//        UserDto.setId(user.getUserId());
//        UserDto.setEmail(user.getUserEmail());
//        UserDto.setName(user.getUsername());
//        UserDto.setPassword("");
//        UserDto.setRole("ADMIN");
//
//        return UserDto;
//    }

    @Transactional
    public void register(UserDto UserDto) {
        User user = new User();
        user.setEmail(UserDto.getEmail());
        user.setName(UserDto.getName());
        user.setPassword(encoder.encode(UserDto.getPassword()));
        user.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
    }

//    @Transactional
//    public void updateUser(Integer userId, UserDto UserDto) {
//        User user = findUserById(userId);
//        user.setUserId(userId);
//        user.setUserEmail(UserDto.getEmail());
//        user.setUsername(UserDto.getName());
//        user.setUserPassword(encoder.encode(UserDto.getPassword()));
//
//        userRepository.save( user );
//    }
//
//    @Transactional
//    public void deleteUser(Integer userId) {
//        User userToDelete = findUserById(userId);
//        userRepository.delete(userToDelete);
//    }
}
