package com.typefigth.match.domain.ports.in.match;

import com.typefigth.match.domain.models.Match;

import java.util.Optional;

public interface GetMatchUseCase {

    Optional<Match> findById(String id);
}
