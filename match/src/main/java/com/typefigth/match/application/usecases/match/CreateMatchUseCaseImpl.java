package com.typefigth.match.application.usecases.match;

import com.typefigth.match.domain.models.Match;
import com.typefigth.match.domain.ports.in.match.CreateMatchUseCase;
import com.typefigth.match.domain.ports.out.match.MatchRepositoryPort;

public class CreateMatchUseCaseImpl implements CreateMatchUseCase {

    private final MatchRepositoryPort matchRepositoryPort;

    public CreateMatchUseCaseImpl(MatchRepositoryPort matchRepositoryPort) {
        this.matchRepositoryPort = matchRepositoryPort;
    }

    @Override
    public Match createMatch(Match match) {
        return this.matchRepositoryPort.createMatch(match);
    }
}
