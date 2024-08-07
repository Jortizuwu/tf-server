package com.typefigth.match.domain.ports.in.match;

import com.typefigth.match.domain.models.Match;

import java.util.List;

public interface ListMatchUseCase {

    List<Match> listMatch();
}
