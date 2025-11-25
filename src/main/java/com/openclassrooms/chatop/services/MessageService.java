package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.model.requestDto.MessageRequestDto;
import com.openclassrooms.chatop.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final RentalService rentalService;
    private final UserService userService;

    @Transactional
    public void createMessage(MessageRequestDto r) {
        Message message = new Message();
        message.setRentalId(rentalService.findRentalByRentalId(r.getRental_id()));
        message.setUserId(userService.findUserById(r.getUser_id()));
        message.setMessage(r.getMessage());
        message.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        message.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        messageRepository.save(message);
    }
}
