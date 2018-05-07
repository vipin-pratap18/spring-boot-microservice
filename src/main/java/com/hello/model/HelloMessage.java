package com.hello.model;

import java.io.Serializable;

public class HelloMessage implements Serializable{

    private String message;
    private String messageId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
