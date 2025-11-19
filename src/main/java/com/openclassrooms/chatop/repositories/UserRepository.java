package com.openclassrooms.chatop.repositories;

import com.openclassrooms.chatop.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
