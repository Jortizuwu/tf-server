package com.typefigth.match.application.usecases.match;

import com.typefigth.match.domain.models.Match;
import com.typefigth.match.domain.ports.in.match.GetMatchUseCase;
import com.typefigth.match.domain.ports.out.match.MatchRepositoryPort;

import java.util.Optional;

public class GetMatchUseCaseImpl implements GetMatchUseCase {

    private final MatchRepositoryPort matchRepositoryPort;

    public GetMatchUseCaseImpl(MatchRepositoryPort matchRepositoryPort) {
        this.matchRepositoryPort = matchRepositoryPort;
    }

    @Override
    public Optional<Match> findById(String id) {
        return this.matchRepositoryPort.findById(id);
    }
}
