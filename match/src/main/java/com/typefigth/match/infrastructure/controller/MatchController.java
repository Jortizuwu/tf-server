package com.typefigth.match.infrastructure.controller;

import com.typefigth.match.application.dtos.match.AssignMatchDto;
import com.typefigth.match.application.dtos.match.CreateMatchDto;
import com.typefigth.match.application.dtos.match.MatchDto;
import com.typefigth.match.application.services.match.ExternalServices;
import com.typefigth.match.application.services.match.MatchService;
import com.typefigth.match.domain.models.Match;
import com.typefigth.match.domain.models.Quote;
import com.typefigth.match.domain.models.User;
import com.typefigth.match.domain.models.enun.Status;
import com.typefigth.match.infrastructure.adapters.mappers.MatchMapper;
import com.typefigth.match.infrastructure.exceptions.CreateQuoteException;
import com.typefigth.match.infrastructure.exceptions.ResourceNotFoundException;
import com.typefigth.match.infrastructure.utils.Constants;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;

    private final Logger logger = LoggerFactory.getLogger(MatchController.class);

    private final ExternalServices externalServices;

    private final MatchMapper matchMapper;

    public MatchController(MatchService matchService, ExternalServices externalServices, MatchMapper matchMapper) {
        this.matchService = matchService;
        this.externalServices = externalServices;
        this.matchMapper = matchMapper;
    }

    @Transactional(readOnly = true)
    @GetMapping()
    public ResponseEntity<List<MatchDto>> listMatches() {
        List<MatchDto> matches = this.matchService.listMatch().stream().map(this::createMatchDtoWithUsersAndQuotesList).toList();
        return ResponseEntity.status(HttpStatus.OK).body(matches);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<MatchDto> getMatch(@PathVariable String id) {
        Match matchDb = this.findMatch(id);
        MatchDto matchDto = this.createMatchDtoWithUsersAndQuotesList(matchDb);

        return ResponseEntity.status(HttpStatus.OK).body(matchDto);
    }

    @Transactional()
    @PostMapping
    public ResponseEntity<MatchDto> createMatch(@Valid @RequestBody CreateMatchDto body) {

        User user = this.externalServices.findUserById(body.getOwnId());

        if (user == null || user.getUid() == null) {
            throw new ResourceNotFoundException(String.format("error user with id: %s not found", body.getOwnId()));
        }

        Match match = new Match();
        match.setOwnId(body.getOwnId());

        Match newMatch = this.matchService.createMatch(match);
        MatchDto matchDto = this.createMatchDtoWithUsersAndQuotesList(newMatch);
        return ResponseEntity.status(HttpStatus.OK).body(matchDto);
    }

    @Transactional()
    @PostMapping("/assign/opponent/{matchId}")
    public ResponseEntity<Object> assignOpponentToMatch(@Valid @RequestBody AssignMatchDto body, @PathVariable String matchId) {

        Match matchDb = this.findMatch(matchId);

        Map<String, Object> response = new HashMap<>();

        if (matchDb.getStatus().equals(Status.CANCELED) || matchDb.getStatus().equals(Status.FINISHED) || matchDb.getStatus().equals(Status.CURRENT) || matchDb.getOwnId().equals(body.getOpponentId()) || matchDb.getOpponentId() != null) {
            String userError = matchDb.getOwnId().equals(body.getOpponentId()) ? "because the own id and opponent id are the same" : "because the opponent is already assigned";
            String error = matchDb.getStatus() != Status.CREATED ? "because the match status is " + matchDb.getStatus().toString() : userError;
            StringBuilder errorMsg = new StringBuilder("Opps!! sorry but can`t assign an opponent to this match, ");

            response.put(Constants.ERROR, errorMsg.append(error).toString());
            response.put(Constants.STATUS, HttpStatus.BAD_REQUEST.toString());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        User opponent = this.externalServices.findUserById(body.getOpponentId());

        if (opponent == null) {
            response.put(Constants.ERROR, "Opps!! sorry but can`t assign an opponent to this match, because user not found");
            response.put(Constants.STATUS, HttpStatus.BAD_REQUEST.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        this.externalServices.createQuote(matchId).doOnError(throwable -> {
            throw new CreateQuoteException(throwable.getMessage());
        });

        Match matchWithOpponent = this.matchService.assignOpponentToMatch(matchDb, body.getOpponentId());
        MatchDto matchDto = this.createMatchDtoWithUsersAndQuotesList(matchWithOpponent);

        return ResponseEntity.status(HttpStatus.OK).body(matchDto);
    }

    @Transactional()
    @PostMapping("/cancel/{matchId}")
    public ResponseEntity<Object> cancelMatch(@PathVariable String matchId) {
        Map<String, Object> response = new HashMap<>();
        Match match = this.findMatch(matchId);

        if (match.getStatus().equals(Status.CANCELED)) {
            response.put(Constants.ERROR, "Opps!! sorry but can`t cancel a canceled match");
            response.put(Constants.STATUS, HttpStatus.BAD_REQUEST.toString());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        if (match.getStatus().equals(Status.FINISHED) || match.getStatus().equals(Status.CURRENT)) {
            response.put(Constants.ERROR, "Opps!! sorry but can`t cancel a finished or current match");
            response.put(Constants.STATUS, HttpStatus.BAD_REQUEST.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        this.matchService.cancelMatch(match);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Transactional()
    @PostMapping("/finish/{matchId}")
    public ResponseEntity<Object> finishMatch(@PathVariable String matchId) {
        Map<String, Object> response = new HashMap<>();

        Match match = this.findMatch(matchId);

        if (match.getStatus().equals(Status.FINISHED) || match.getStatus().equals(Status.CANCELED)) {
            response.put(Constants.ERROR, "Opps!! sorry but can`t finish a canceled or finished match");
            response.put(Constants.STATUS, HttpStatus.BAD_REQUEST.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        this.matchService.finishMatch(match);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    private MatchDto createMatchDtoWithUsersAndQuotesList(Match match) {
        List<String> usersId = List.of(match.getOwnId(), match.getOpponentId() != null ? match.getOpponentId() : "");
        List<User> users = new ArrayList<>();
        for (String id : usersId) {
            if (!id.isEmpty()) {
                User user = this.externalServices.findUserById(id);
                if (user != null) {
                    users.add(user);
                }
            }
        }

        List<Quote> quotes = this.externalServices.listQuoteByMatchId(match.getId());

        return matchMapper.toDto(match, users, quotes);
    }

    private Match findMatch(String id) throws ResourceNotFoundException {
        return this.matchService.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("error match with id: %s not found", id)));
    }


}
