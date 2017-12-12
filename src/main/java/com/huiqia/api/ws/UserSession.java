package com.huiqia.api.ws;

import com.huiqia.api.model.User;

import javax.websocket.Session;

public class UserSession {
    private Session session;
    private User user;

    public UserSession() {
    }

    public UserSession(Session session, User user) {
        this.session = session;
        this.user = user;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
