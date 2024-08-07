package com.typefigth.match.domain.ports.in.match;

import com.typefigth.match.domain.models.Match;

public interface CreateMatchUseCase {

    Match createMatch(Match match);

}
