package com.example.springsecurity.email;

public interface EmailSender {
    void send(String to, String email);
}
