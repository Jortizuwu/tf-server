package com.typefigth.match.application.usecases.match;

import com.typefigth.match.domain.models.Match;
import com.typefigth.match.domain.ports.in.match.CancelMatchUseCase;
import com.typefigth.match.domain.ports.in.match.FinishMatchUseCase;
import com.typefigth.match.domain.ports.out.match.MatchRepositoryPort;

public class FinishMatchUseCaseImpl implements FinishMatchUseCase {

    private final MatchRepositoryPort matchRepositoryPort;

    public FinishMatchUseCaseImpl(MatchRepositoryPort matchRepositoryPort) {
        this.matchRepositoryPort = matchRepositoryPort;
    }

    @Override
    public Match finishMatch(Match match) {
        return this.matchRepositoryPort.finishMatch(match);
    }
}
