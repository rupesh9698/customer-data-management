package com.dm.customer.dto;

/**
 * Message class used in message response class
 */
public class Message {

    private String messages;

    public Message() {
    }

    public Message(String messages) {
        this.messages = messages;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}
