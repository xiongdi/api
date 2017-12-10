package com.huiqia.api.ws;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

@Component
@ServerEndpoint(value = "/chat")
public class ChatWebSocket {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
//        logger.info("{}", session.());
//        logger.info("{}", config.getUserProperties());
//        logger.info(config.getUserProperties().get("username").toString());
        session.getAsyncRemote().sendText("opened");
        logger.info("已连接");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("{}", session.getQueryString());
        session.getAsyncRemote().sendText("do you say: " + message);
        logger.info("收到消息");
    }

//    @OnMessage
//    public void onMessage(byte[] bytes, boolean last, Session session) throws Exception {
//        session.getBasicRemote().sendBinary(ByteBuffer.wrap(bytes));
//    }

    @OnClose
    public void onClose(Session session, CloseReason reason) throws Exception {
        session.close(reason);
        logger.info(reason.getReasonPhrase());
        logger.info("已关闭");
    }

    @OnError
    public void onError(Session session, Throwable error) {
        session.getAsyncRemote().sendText("error!");
        logger.error(error.getMessage());
    }
}
