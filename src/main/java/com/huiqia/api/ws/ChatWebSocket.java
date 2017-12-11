package com.huiqia.api.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huiqia.api.model.WebSocketEvent;
import com.huiqia.api.model.WebSocketEventLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

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
    public void onMessage(String message, Session session) throws Exception {
        logger.info(message);

        ObjectMapper mapper = new ObjectMapper();

        WebSocketEvent value = mapper.readValue(message, WebSocketEvent.class);

        switch (value.getEventType()) {
            case LOGIN:
                WebSocketEventLogin webSocketEventLogin = mapper.readValue(value.getMsg(), WebSocketEventLogin.class);
            case LOGOUT:

            case CHAT:
            default:
        }

//        WebSocketEvent value = JSON.parseObject(message, WebSocketEvent.class);
        logger.info("{}", session.getQueryString());

        session.getAsyncRemote().sendText(mapper.writeValueAsString(value));
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
