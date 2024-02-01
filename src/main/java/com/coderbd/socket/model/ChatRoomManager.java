package com.coderbd.socket.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ChatRoomManager {

    private final Map<String, ChatRoom> chatRooms = new HashMap<>();

    public void createChatRoom(String chatRoomId) {
        chatRooms.put(chatRoomId, new ChatRoom());
    }

    public ChatRoom getChatRoom(String chatRoomId) {
        return chatRooms.get(chatRoomId);
    }

    public Map<String, ChatRoom> getAllChatRooms() {
        return chatRooms;
    }
}
