package com.coderbd.socket.controller;


import com.coderbd.socket.model.ChatRoomManager;
import com.coderbd.socket.model.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@AllArgsConstructor
@Controller
@Slf4j
//@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {


    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatRoomManager chatRoomManager;


    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
        log.info(message.toString());
        return message;
    }

    @MessageMapping("/join-room")
    public void joinRoom(@Payload Message message) {
        String chatRoomId = message.getMessage();
        chatRoomManager.getChatRoom(chatRoomId).addUser(message.getSenderName());
        simpMessagingTemplate.convertAndSend("/chatroom/" + chatRoomId + "/users",
                chatRoomManager.getChatRoom(chatRoomId).getUsers());
    }

    @MessageMapping("/create-room")
    public void createRoom(@Payload Message message) {
        String chatRoomId = message.getMessage();
        chatRoomManager.createChatRoom(chatRoomId);
        log.info("Created chatroom: " + chatRoomId);
    }
}
