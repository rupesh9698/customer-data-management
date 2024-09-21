package com.dm.customer.dto;

import com.dm.customer.exception.RestApiResponse;

import java.util.List;

/**
 * Message response class to pass the details in http response
 */
public class MessageResponse implements RestApiResponse {

    private String status;
    private int statusCode;
    private String code;
    private List<Message> messages;

    public MessageResponse() {
    }

    public MessageResponse(String status, int statusCode, String code, List<Message> messages) {
        this.status = status;
        this.statusCode = statusCode;
        this.code = code;
        this.messages = messages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
