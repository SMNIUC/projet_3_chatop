package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.model.requestDto.MessageRequestDto;
import com.openclassrooms.chatop.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "Message Controller", description = "CRUD operations for Messages")
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @Operation(summary = "Send a message to rental owner")
    @PostMapping("")
    public ResponseEntity<Map<String, String>> sendMessage(@RequestBody MessageRequestDto r){
        messageService.createMessage(r);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Message sent with success");

        return ResponseEntity.ok(response);
    }
}
