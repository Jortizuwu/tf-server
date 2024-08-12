package com.typefigth.match.application.usecases.match;


import com.typefigth.match.domain.models.Match;
import com.typefigth.match.domain.ports.in.match.AssignOpponentToMatchUseCase;
import com.typefigth.match.domain.ports.out.match.MatchRepositoryPort;


public class AssignOpponentToMatchUseCaseImpl implements AssignOpponentToMatchUseCase {

    private final MatchRepositoryPort matchRepositoryPort;

    public AssignOpponentToMatchUseCaseImpl(MatchRepositoryPort matchRepositoryPort) {
        this.matchRepositoryPort = matchRepositoryPort;
    }

    @Override
    public Match assignOpponentToMatch(Match match, String opponentId) {
        return this.matchRepositoryPort.assignOpponentToMatch(match, opponentId);
    }
}
