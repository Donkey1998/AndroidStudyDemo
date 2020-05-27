package com.example.androidstudydemo.EventBusDemo;

public class EventMessage {
    private String  messageType;
    private String  message;

    public EventMessage(String messageType, String message) {
        this.messageType = messageType;
        this.message = message;
    }

    @Override
    public String toString() {
        return "EventMessage{" +
                "messageType='" + messageType + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
