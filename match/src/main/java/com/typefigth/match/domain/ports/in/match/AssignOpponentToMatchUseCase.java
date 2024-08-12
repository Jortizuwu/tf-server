package com.typefigth.match.domain.ports.in.match;

import com.typefigth.match.domain.models.Match;

public interface AssignOpponentToMatchUseCase {
    Match assignOpponentToMatch(Match match, String opponentId);
}
