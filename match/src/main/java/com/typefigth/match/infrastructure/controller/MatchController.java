package com.typefigth.match.infrastructure.controller;

import com.typefigth.match.application.response.Response;
import com.typefigth.match.application.services.match.MatchService;
import com.typefigth.match.domain.models.Match;
import com.typefigth.match.domain.models.User;
import com.typefigth.match.infrastructure.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(MatchController.class);

    private final WebClient webClient;

    public MatchController(MatchService matchService, WebClient webClient) {
        this.matchService = matchService;
        this.webClient = webClient;
    }

    @GetMapping()
    public ResponseEntity<Response<List<Match>>> listMatches(HttpServletRequest request) {
        ParameterizedTypeReference<Response<User>> responseType = new ParameterizedTypeReference<Response<User>>() {
        };

        List<Match> matches = this.matchService.listMatch().stream().map(value -> {
            Response<User> userResponse = this.webClient.get().uri("/user/" + value.getOwnId()).retrieve().bodyToMono(responseType).block();

            if (userResponse != null && userResponse.getData() != null) {
                value.setUser(userResponse.getData());
            }
            return value;
        }).toList();

        Response<List<Match>> response = Response.build(200, request.getContextPath(), true, matches, false, getClientIp(request), "OK", HttpStatus.OK.toString());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getMatch(@PathVariable String id, HttpServletRequest request) {
        Match match = this.matchService.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("error match with id: %s not found", id)));
        Response response = Response.build(200, request.getContextPath(), true, match, false, getClientIp(request), "OK", HttpStatus.OK.toString());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Response> createMatch(@RequestBody Match body, HttpServletRequest request) {
        Match newMatch = this.matchService.createMatch(body);
        Response response = Response.build(200, request.getContextPath(), true, newMatch, false, getClientIp(request), "OK", HttpStatus.OK.toString());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || remoteAddr.isEmpty()) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
}
