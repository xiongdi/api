package com.huiqia.api.model;

import java.util.Date;

public class ChatMessage {
    private User from;
    private User to;
    private String content;
    private Date date;

    public ChatMessage() {
    }

    public ChatMessage(User from, User to, String content, Date date) {
        this.from = from;
        this.to = to;
        this.content = content;
        this.date = date;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
