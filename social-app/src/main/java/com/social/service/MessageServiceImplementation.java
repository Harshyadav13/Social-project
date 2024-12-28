package com.social.service;

import com.social.models.Chat;
import com.social.models.Message;
import com.social.models.User;
import com.social.repository.ChatRepository;
import com.social.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImplementation implements MessageService {

            @Autowired
            private MessageRepository messageRepository;

            @Autowired
            private ChatService chatService;

            @Autowired
            private ChatRepository chatRepository;

            public Message createMessage(User user, Integer chatId, Message req) throws Exception {

                Message message = new Message();

                Chat chat = chatService.findChatById(chatId);

                message.setChat(chat);
                message.setContent(req.getContent());
                message.setImage(req.getImage());
                message.setUser(user);
                message.setTimestamp(LocalDateTime.now());

                Message savedMessage = messageRepository.save(message);

                chat.getMessages().add(savedMessage);
                chatRepository.save(chat);

                return savedMessage ;
            }

            public List<Message> findChatsMessages(Integer chatId) throws Exception {

                Chat chat = chatService.findChatById(chatId);

                return messageRepository.findByChatId(chatId);
            }
}
