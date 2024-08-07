package com.typefigth.match.application.services.match;

import com.typefigth.match.domain.models.Match;
import com.typefigth.match.domain.ports.in.match.CreateMatchUseCase;
import com.typefigth.match.domain.ports.in.match.GetMatchUseCase;
import com.typefigth.match.domain.ports.in.match.ListMatchUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService implements CreateMatchUseCase, GetMatchUseCase, ListMatchUseCase {

    private final CreateMatchUseCase createMatchUseCase;

    private final GetMatchUseCase getMatchUseCase;

    private final ListMatchUseCase listMatchUseCase;

    public MatchService(CreateMatchUseCase createMatchUseCase, GetMatchUseCase getMatchUseCase, ListMatchUseCase listMatchUseCase) {
        this.createMatchUseCase = createMatchUseCase;
        this.getMatchUseCase = getMatchUseCase;
        this.listMatchUseCase = listMatchUseCase;
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
}

