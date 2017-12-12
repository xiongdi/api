package com.huiqia.api.model;

import java.util.Date;

public class WebSocketEventChat {
    private String content;
    private Date date;

    public WebSocketEventChat() {
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
