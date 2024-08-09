package com.typefigth.match.infrastructure.controller;

import com.typefigth.match.application.dtos.match.CreateMatchDto;
import com.typefigth.match.application.dtos.match.MatchDto;
import com.typefigth.match.application.response.Response;
import com.typefigth.match.application.services.match.MatchService;
import com.typefigth.match.domain.models.Match;
import com.typefigth.match.domain.models.User;
import com.typefigth.match.infrastructure.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    @GetMapping()
    public ResponseEntity<Response<List<MatchDto>>> listMatches(HttpServletRequest request) {

        List<MatchDto> matches = this.matchService.listMatch().stream().map(value -> this.createMatchDtoWithOwn(value, value.getOwnId())).toList();

        Response<List<MatchDto>> response = Response.build(
                200,
                request.getContextPath(),
                true, matches,
                false,
                getClientIp(request),
                "OK",
                HttpStatus.OK.toString());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<MatchDto>> getMatch(@PathVariable String id, HttpServletRequest request) {
        Match match = this.matchService.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("error match with id: %s not found", id)));

        MatchDto matchDto = createMatchDtoWithOwn(match, match.getOwnId());

        Response<MatchDto> response = Response.build(200,
                request.getContextPath(),
                true,
                matchDto,
                false,
                getClientIp(request),
                "OK",
                HttpStatus.OK.toString());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Response<MatchDto>> createMatch(@Valid @RequestBody CreateMatchDto body, HttpServletRequest request,  BindingResult bindingResult) {

        Match match = new Match();
//        if (bindingResult.hasErrors()) {
//            return ResponseEntity.badRequest().body("Error en la validación");
//        }
        match.setOwnId(body.getOwnId());
        Match newMatch = this.matchService.createMatch(match);

        MatchDto matchDto = createMatchDtoWithOwn(newMatch, body.getOwnId());

        Response<MatchDto> response = Response.build(200,
                request.getContextPath(),
                true,
                matchDto,
                false,
                getClientIp(request),
                "OK",
                HttpStatus.OK.toString());
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

    private MatchDto createMatchDtoWithOwn(Match match, String id) {
        ParameterizedTypeReference<Response<User>> responseType = new ParameterizedTypeReference<>() {
        };

        MatchDto matchDto = new MatchDto(match.getId(), match.getOwnId(), null, match.getCreatedAt(), match.getUpdatedAt());

        Response<User> userResponse = this.webClient
                .get()
                .uri("/user/" + id)
                .retrieve()
                .bodyToMono(responseType)
                .onErrorResume(this::apply).block();

        if (userResponse != null && userResponse.getData() != null) {
            matchDto.setOwn(userResponse.getData());
        }
        return matchDto;
    }

    private Mono<? extends Response<User>> apply(Throwable e) {
        logger.error("Error fetching user data: {}", e.getMessage());
        return Mono.just(new Response<>());
    }
}
