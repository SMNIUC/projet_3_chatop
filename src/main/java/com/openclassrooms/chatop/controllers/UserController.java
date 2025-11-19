package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
}
