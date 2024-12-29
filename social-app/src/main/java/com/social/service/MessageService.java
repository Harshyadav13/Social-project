package com.social.service;

import com.social.exception.ChatException;
import com.social.exception.MessageException;
import com.social.models.Chat;
import com.social.models.Message;
import com.social.models.User;

import java.util.List;

public interface MessageService {

    public  Message createMessage(User user , Integer chatId, Message req) throws MessageException, ChatException;


    public List<Message> findChatsMessages(Integer chatId) throws MessageException, ChatException;


}
