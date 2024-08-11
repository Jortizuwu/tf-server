package com.typefigth.match.infrastructure.controller;

import com.typefigth.match.application.dtos.match.CreateMatchDto;
import com.typefigth.match.application.dtos.match.MatchDto;
import com.typefigth.match.application.services.match.MatchService;
import com.typefigth.match.domain.models.Match;
import com.typefigth.match.domain.models.User;
import com.typefigth.match.infrastructure.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;

    private final Logger logger = LoggerFactory.getLogger(MatchController.class);

    private final WebClient webClient;

    public MatchController(MatchService matchService, WebClient webClient) {
        this.matchService = matchService;
        this.webClient = webClient;
    }

    @Transactional(readOnly = true)
    @GetMapping()
    public ResponseEntity<List<MatchDto>> listMatches() {
        List<MatchDto> matches = this.matchService.listMatch().stream().map(value -> this.createMatchDtoWithOwn(value, value.getOwnId())).toList();
        return ResponseEntity.status(HttpStatus.OK).body(matches);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<MatchDto> getMatch(@PathVariable String id) {
        Match match = this.matchService.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("error match with id: %s not found", id)));
        MatchDto matchDto = createMatchDtoWithOwn(match, match.getOwnId());
        return ResponseEntity.status(HttpStatus.OK).body(matchDto);
    }

    @Transactional()
    @PostMapping
    public ResponseEntity<MatchDto> createMatch(@Valid @RequestBody CreateMatchDto body) {

        Match match = new Match();
        match.setOwnId(body.getOwnId());

        Match newMatch = this.matchService.createMatch(match);

        MatchDto matchDto = createMatchDtoWithOwn(newMatch, body.getOwnId());
        return ResponseEntity.status(HttpStatus.OK).body(matchDto);
    }


    private MatchDto createMatchDtoWithOwn(Match match, String id) {
        MatchDto matchDto = new MatchDto(match.getId(), match.getOwnId(), null, match.getCreatedAt(), match.getUpdatedAt());
        User userResponse = findUserById(id);
        if (userResponse != null) {
            matchDto.setOwn(userResponse);
        }
        return matchDto;
    }

    private User findUserById(String id) {
        return webClient.get()
                .uri("http://localhost:8080/user/" + id)
                .retrieve()
                .bodyToMono(User.class)
                .onErrorResume(this::apply)
                .block();
    }

    private Mono<? extends User> apply(Throwable e) {
        logger.error("Error fetching user data: {}", e.getMessage());
        return Mono.just(new User());
    }
}
