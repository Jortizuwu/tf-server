package com.typefigth.match.infrastructure.adapters.mappers;


import com.typefigth.match.application.dtos.match.MatchDto;
import com.typefigth.match.domain.models.Match;
import com.typefigth.match.domain.models.Quote;
import com.typefigth.match.domain.models.User;
import com.typefigth.match.infrastructure.entities.match.MatchEntity;

import java.util.List;

public interface MatchMapper {

    MatchEntity toEntity(Match match);

    Match fromEntity(MatchEntity entity);

    MatchDto toDto(Match match, List<User> users, List<Quote> quotes);
}
