package com.huiqia.api.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huiqia.api.dao.UserRepository;
import com.huiqia.api.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint(value = "/chat")
public class ChatWebSocket {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private final CopyOnWriteArraySet<UserSession> set = new CopyOnWriteArraySet<UserSession>();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StringRedisTemplate template;

//    private RedisTemplate<String, User> template;

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
            case OPENED:
                WebSocketEventLogin webSocketEventLogin = mapper.readValue(value.getMsg(), WebSocketEventLogin.class);
//                User user = userRepository.findUserById(webSocketEventLogin.getId());
//                set.add(new UserSession(session, user));
//                template.boundValueOps(session.getId()).set(webSocketEventLogin.getId().toString());
//                template.boundHashOps("ws").put(session.getId(), webSocketEventLogin.getId());
                WebSocketEventLogin msg = new WebSocketEventLogin(webSocketEventLogin.getId());
                WebSocketEvent response = new WebSocketEvent(WebSocketEventType.OPENED, new Date(), mapper.writeValueAsString(msg));
                session.getBasicRemote().sendText(mapper.writeValueAsString(response));
                break;
            case CLOSE:
                session.close();
            case CHAT:
                WebSocketEventChat chat = mapper.readValue(value.getMsg(), WebSocketEventChat.class);
//                for(Object id : template.boundHashOps("ws").keys()) {
//                    String sid = (String) id;
//
//                    session.getOpenSessions();
//                }

                for (Session s : session.getOpenSessions()) {
                    s.getAsyncRemote().sendText(mapper.writeValueAsString(chat));
                }
            default:
        }

//        WebSocketEvent value = JSON.parseObject(message, WebSocketEvent.class);
        logger.info("{}", session.getQueryString());

//        session.getAsyncRemote().sendText(mapper.writeValueAsString(value));
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
