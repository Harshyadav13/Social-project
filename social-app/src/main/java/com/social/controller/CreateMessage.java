package com.social.controller;

import com.social.models.Message;
import com.social.models.User;
import com.social.service.MessageService;
import com.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreateMessage {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/messages/chat/{chatId}")
    public Message createMessage(@RequestBody Message req, @RequestHeader("Authorization") String jwt, @PathVariable Integer chatId) throws Exception {

        User user = userService.findUserByJwt(jwt);
        Message message = messageService.createMessage(user, chatId, req);
        return message;
    }

    @GetMapping("/api/messages/chat/{chatId}")
    public List<Message> createMessage(@RequestHeader("Authorization") String jwt, @PathVariable Integer chatId) throws Exception {

        User user = userService.findUserByJwt(jwt);

        List<Message> messages = messageService.findChatsMessages(chatId);

        return messages;
    }
}
