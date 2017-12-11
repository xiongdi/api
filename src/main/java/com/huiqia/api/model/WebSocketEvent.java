package com.huiqia.api.model;

import java.util.Date;

public class WebSocketEvent {
    private WebSocketEventType eventType;
    private Date date;
    private String msg;

    public WebSocketEvent() {
    }

    public WebSocketEvent(WebSocketEventType eventType, Date date, String msg) {
        this.eventType = eventType;
        this.date = date;
        this.msg = msg;
    }

    public WebSocketEventType getEventType() {
        return eventType;
    }

    public void setEventType(WebSocketEventType eventType) {
        this.eventType = eventType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
