package com.typefigth.match.infrastructure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

    Logger logger = LoggerFactory.getLogger(SocketController.class);

    @MessageMapping("/room/{roomId}")
    @SendTo("/room/{roomId}")
    public String matchRoom(@DestinationVariable String roomId, String message) {
        this.logger.info("{}", message + roomId);
        return message;
    }
}
