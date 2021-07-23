package com.dihu;

import java.io.Serializable;

public class Message implements Serializable {
    private String from;
    private String to;
    private String text;

    public Message() {

    }
    public Message(String from,String text) {
        this.from = from;
        this.text = text;
        this.to = "all";
    }
    public Message(String from,String text,String to) {
        this.from = from;
        this.text = text;
        this.to = to;
    }
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}