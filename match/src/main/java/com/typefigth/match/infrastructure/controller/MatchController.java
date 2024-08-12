package com.typefigth.match.infrastructure.controller;

import com.typefigth.match.application.dtos.match.AssignMatchDto;
import com.typefigth.match.application.dtos.match.CreateMatchDto;
import com.typefigth.match.application.dtos.match.MatchDto;
import com.typefigth.match.application.services.match.MatchService;
import com.typefigth.match.domain.models.Match;
import com.typefigth.match.domain.models.User;
import com.typefigth.match.domain.models.enun.Status;
import com.typefigth.match.infrastructure.adapters.mappers.MatchMapper;
import com.typefigth.match.infrastructure.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;

    private final Logger logger = LoggerFactory.getLogger(MatchController.class);

    private final WebClient webClient;

    private final MatchMapper matchMapper;

    public MatchController(MatchService matchService, WebClient webClient, MatchMapper matchMapper) {
        this.matchService = matchService;
        this.webClient = webClient;
        this.matchMapper = matchMapper;
    }

    @Transactional(readOnly = true)
    @GetMapping()
    public ResponseEntity<List<MatchDto>> listMatches() {
        List<MatchDto> matches = this.matchService.listMatch().stream().map(this::createMatchDtoWithUsersList).toList();
        return ResponseEntity.status(HttpStatus.OK).body(matches);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<MatchDto> getMatch(@PathVariable String id) {
        Match matchDb = this.findMatch(id);
        MatchDto matchDto = this.createMatchDtoWithUsersList(matchDb);
        return ResponseEntity.status(HttpStatus.OK).body(matchDto);
    }

    @Transactional()
    @PostMapping
    public ResponseEntity<MatchDto> createMatch(@Valid @RequestBody CreateMatchDto body) {
        Match match = new Match();
        match.setOwnId(body.getOwnId());
        Match newMatch = this.matchService.createMatch(match);
        MatchDto matchDto = this.createMatchDtoWithUsersList(newMatch);
        return ResponseEntity.status(HttpStatus.OK).body(matchDto);
    }

    @Transactional()
    @PostMapping("/assign/opponent/{matchId}")
    public ResponseEntity<Object> assignOpponentToMatch(@Valid @RequestBody AssignMatchDto body, @PathVariable String matchId) {

        Match matchDb = this.findMatch(matchId);

        Map<String, Object> response = new HashMap<>();

        if (matchDb.getStatus().equals(Status.CANCELED)
                || matchDb.getStatus().equals(Status.FINISHED)
                || matchDb.getStatus().equals(Status.CURRENT)
                || matchDb.getOwnId().equals(body.getOpponentId())
        ) {
            String error = matchDb.getStatus().toString();
            StringBuilder errorMsg = new StringBuilder("Opps!! sorry but can`t assign an opponent to this match, because is ");

            response.put("error", errorMsg.append(error).toString());
            response.put("status", HttpStatus.BAD_REQUEST.toString());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        User opponent = this.findUserById(body.getOpponentId());

        if (opponent == null) {
            response.put("error", "Opps!! sorry but can`t assign an opponent to this match, because user not found");
            response.put("status", HttpStatus.BAD_REQUEST.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Match matchWithOpponent = this.matchService.assignOpponentToMatch(matchDb, body.getOpponentId());
        MatchDto matchDto = this.createMatchDtoWithUsersList(matchWithOpponent);

        return ResponseEntity.status(HttpStatus.OK).body(matchDto);
    }

    @Transactional()
    @PostMapping("/cancel/{matchId}")
    public ResponseEntity<Object> cancelMatch(@PathVariable String matchId) {
        Match match = this.findMatch(matchId);

        this.matchService.cancelMatch(match);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Transactional()
    @PostMapping("/finish/{matchId}")
    public ResponseEntity<Object> finishMatch(@PathVariable String matchId) {
        Match match = this.findMatch(matchId);

        this.matchService.finishMatch(match);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
    private MatchDto createMatchDtoWithUsersList(Match match) {
        List<String> usersId = List.of(match.getOwnId(), match.getOpponentId() != null ? match.getOpponentId() : "");
        List<User> users = new ArrayList<>();
        for (String id : usersId) {
            if (!id.isEmpty()) {
                User user = findUserById(id);
                if (user != null) {
                    users.add(user);
                }
            }
        }
        return matchMapper.toDto(match, users);
    }

    private Match findMatch(String id) {
        return this.matchService.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("error match with id: %s not found", id)));
    }

    private User findUserById(String id) {
        return webClient.get().uri("http://localhost:8080/user/" + id).retrieve().bodyToMono(User.class).onErrorResume(this::apply).block();
    }

    private Mono<? extends User> apply(Throwable e) {
        logger.error("Error fetching user data: {}", e.getMessage());
        return Mono.just(new User());
    }
}
