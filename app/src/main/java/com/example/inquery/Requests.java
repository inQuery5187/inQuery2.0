package com.example.inquery;

public class Requests {
    public String type;
    public String sender;
    public String reason;
    public String UID;


    public Requests(String type, String sender, String reason) {
        this.type = type;
        this.sender = sender;
        this.reason = reason;
    }
    public Requests(String type, String sender, String reason, String UID) {
        this.type = type;
        this.sender = sender;
        this.reason = reason;
        this.UID = UID;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
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