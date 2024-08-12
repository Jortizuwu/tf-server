package com.typefigth.match.infrastructure.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

    @MessageMapping("/match/room/{roomId}")
    @SendTo("/queue/match/room/{roomId}")
    public String matchRoom(@DestinationVariable String roomId, String message) {
        return message;
    }
}
