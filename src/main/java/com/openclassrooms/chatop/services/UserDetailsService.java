package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.configuration.MyUserDetails;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws BadCredentialsException {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new BadCredentialsException("Could not find user");
        }
        return new MyUserDetails(user);
    }
}
