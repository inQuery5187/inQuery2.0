package com.example.inquery;

public class Requests {
    public String type;
    public String sender;
    public String reason;


    public Requests(String type, String sender, String reason) {
        this.type = type;
        this.sender = sender;
        this.reason = reason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}