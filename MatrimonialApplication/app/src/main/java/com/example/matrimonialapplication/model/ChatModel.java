package com.example.matrimonialapplication.model;

public class ChatModel {
    private String sender;
    private String message;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ChatModel() {
    }

    public ChatModel(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }
}
