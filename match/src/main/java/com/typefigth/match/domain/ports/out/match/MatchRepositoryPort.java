package com.typefigth.match.domain.ports.out.match;

import com.typefigth.match.domain.models.Match;

import java.util.List;
import java.util.Optional;

public interface MatchRepositoryPort {
    Optional<Match> getMatch(String id);

    List<Match> listMatch(String id);

    Math createMatch(Match match);
}
