package com.typefigth.match.domain.ports.in.match;

import com.typefigth.match.domain.models.Match;

public interface FinishMatchUseCase {

    Match finishMatch(Match match);

}
