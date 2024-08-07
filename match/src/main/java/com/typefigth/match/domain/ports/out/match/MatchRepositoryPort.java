package com.typefigth.match.domain.ports.out.match;

import com.typefigth.match.domain.models.Match;

import java.util.List;
import java.util.Optional;

public interface MatchRepositoryPort {
    Optional<Match> findById(String id);

    List<Match> listMatch();

    Match createMatch(Match match);
}
