package com.typefigth.match.application.usecases.match;

import com.typefigth.match.domain.models.Match;
import com.typefigth.match.domain.ports.in.match.CancelMatchUseCase;
import com.typefigth.match.domain.ports.out.match.MatchRepositoryPort;

public class CancelMatchUseCaseImpl implements CancelMatchUseCase {

    private final MatchRepositoryPort matchRepositoryPort;

    public CancelMatchUseCaseImpl(MatchRepositoryPort matchRepositoryPort) {
        this.matchRepositoryPort = matchRepositoryPort;
    }

    @Override
    public Match cancelMatch(Match match) {
        return this.matchRepositoryPort.cancelMatch(match);
    }
}
