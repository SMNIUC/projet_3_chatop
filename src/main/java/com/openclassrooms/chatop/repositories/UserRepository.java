package com.openclassrooms.chatop.repositories;

import com.openclassrooms.chatop.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String userEmail);
    Optional<User> findByEmailIgnoreCase(String userEmail);
//    Optional<User> findByName(String userName);
}
