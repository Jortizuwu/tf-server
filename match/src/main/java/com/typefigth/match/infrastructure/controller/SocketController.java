package com.typefigth.match.infrastructure.controller;

import com.typefigth.match.application.services.match.MatchService;
import com.typefigth.match.domain.models.Match;
import com.typefigth.match.infrastructure.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Controller
public class SocketController {

    private final MatchService matchService;
    private final WebClient webClient;
    private final Logger logger = LoggerFactory.getLogger(SocketController.class);
    private Match match = new Match();

    public SocketController(MatchService matchService, WebClient webClient) {
        this.matchService = matchService;
        this.webClient = webClient;
    }

    @MessageMapping("/room/{roomId}")
    @SendTo("/room/{roomId}")
    public String startMatchRoom(@DestinationVariable String roomId, String message) {

        if (match.getId() == null) {
            Optional<Match> optionalMatch = matchService.findById(roomId);
            if (optionalMatch.isEmpty()) {
                throw new ResourceNotFoundException("Match not found");
            }
            match = optionalMatch.get();
        }

        this.logger.info("{}", match.getOpponentId());

        String messageWithRoomId = message + roomId;

        this.logger.info("{}", messageWithRoomId);
        return message;
    }

}
