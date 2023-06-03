package com.cgsoft.ws.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyWebSocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOGGER.info("Connection established: {}", session.getId());
        super.afterConnectionEstablished(session);

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status)  {
        LOGGER.info("Connection closed: {} ({})", session.getId(), status.getReason());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        LOGGER.info("Message received: {} (from {})", message.getPayload(), session.getId());
        session.sendMessage(new TextMessage( message.getPayload()));
    }
}
