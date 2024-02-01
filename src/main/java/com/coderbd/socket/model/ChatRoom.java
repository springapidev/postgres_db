package com.coderbd.socket.model;

import java.util.HashSet;
import java.util.Set;

public class ChatRoom {

    private final Set<String> users = new HashSet<>();

    public void addUser(String username) {
        users.add(username);
    }

    public void removeUser(String username) {
        users.remove(username);
    }

    public Set<String> getUsers() {
        return users;
    }
}
