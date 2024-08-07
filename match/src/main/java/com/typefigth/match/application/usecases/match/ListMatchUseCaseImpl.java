package com.typefigth.match.application.usecases.match;

import com.typefigth.match.domain.models.Match;
import com.typefigth.match.domain.ports.in.match.ListMatchUseCase;
import com.typefigth.match.domain.ports.out.match.MatchRepositoryPort;

import java.util.List;

public class ListMatchUseCaseImpl implements ListMatchUseCase {

    private final MatchRepositoryPort matchRepositoryPort;

    public ListMatchUseCaseImpl(MatchRepositoryPort matchRepositoryPort) {
        this.matchRepositoryPort = matchRepositoryPort;
    }

    @Override
    public List<Match> listMatch() {
        return this.matchRepositoryPort.listMatch();
    }
}
