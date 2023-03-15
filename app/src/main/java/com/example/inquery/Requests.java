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
}