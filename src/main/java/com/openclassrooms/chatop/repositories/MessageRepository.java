package com.openclassrooms.chatop.repositories;

import com.openclassrooms.chatop.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {
}
