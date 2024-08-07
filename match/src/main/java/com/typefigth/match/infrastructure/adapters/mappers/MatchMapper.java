package com.typefigth.match.infrastructure.adapters.mappers;


import com.typefigth.match.domain.models.Match;
import com.typefigth.match.infrastructure.entities.match.MatchEntity;

public interface MatchMapper {

    MatchEntity toEntity(Match match);
    Match fromEntity(MatchEntity entity);
}
