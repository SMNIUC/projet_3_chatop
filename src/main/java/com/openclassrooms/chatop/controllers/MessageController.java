package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
}
