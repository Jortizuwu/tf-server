package com.typefigth.match.application.services.match;

import com.typefigth.match.domain.models.Match;
import com.typefigth.match.domain.ports.in.match.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService implements CreateMatchUseCase, GetMatchUseCase, ListMatchUseCase, AssignOpponentToMatchUseCase, CancelMatchUseCase, FinishMatchUseCase {

    private final CreateMatchUseCase createMatchUseCase;

    private final GetMatchUseCase getMatchUseCase;

    private final ListMatchUseCase listMatchUseCase;

    private final AssignOpponentToMatchUseCase assignOpponentToMatchUseCase;

    private final CancelMatchUseCase cancelMatchUseCase;

    private final FinishMatchUseCase finishMatchUseCase;

    public MatchService(
            CreateMatchUseCase createMatchUseCase,
            GetMatchUseCase getMatchUseCase,
            ListMatchUseCase listMatchUseCase,
            AssignOpponentToMatchUseCase assignOpponentToMatchUseCase,
            CancelMatchUseCase cancelMatchUseCase,
            FinishMatchUseCase finishMatchUseCase
    ) {
        this.createMatchUseCase = createMatchUseCase;
        this.getMatchUseCase = getMatchUseCase;
        this.listMatchUseCase = listMatchUseCase;
        this.assignOpponentToMatchUseCase = assignOpponentToMatchUseCase;
        this.cancelMatchUseCase = cancelMatchUseCase;
        this.finishMatchUseCase = finishMatchUseCase;
    }

    @Override
    public Match createMatch(Match match) {
        return this.createMatchUseCase.createMatch(match);
    }

    @Override
    public Optional<Match> findById(String id) {
        return this.getMatchUseCase.findById(id);
    }

    @Override
    public List<Match> listMatch() {
        return this.listMatchUseCase.listMatch();
    }

    @Override
    public Match assignOpponentToMatch(Match match, String opponentId) {
        return this.assignOpponentToMatchUseCase.assignOpponentToMatch(match, opponentId);
    }

    @Override
    public Match cancelMatch(Match match) {
        return this.cancelMatchUseCase.cancelMatch(match);
    }

    @Override
    public Match finishMatch(Match match) {
        return this.finishMatchUseCase.finishMatch(match);
    }
}

