package com.typefigth.match.infrastructure.adapters.mappers;

import com.typefigth.match.domain.models.Match;
import com.typefigth.match.infrastructure.entities.match.MatchEntity;

public class MatchMapperAdapter implements MatchMapper {

    public MatchEntity toEntity(Match match) {
        return new MatchEntity.MatchBuilder()
                .setId(match.getId())
                .setCreatedAt(match.getCreatedAt())
                .setUpdatedAt(match.getUpdatedAt())
                .build();

    }

    public Match fromEntity(MatchEntity entity) {
        return new Match.MatchBuilder()
                .setId(entity.getId())
                .setCreatedAt(entity.getCreatedAt())
                .setUpdatedAt(entity.getUpdatedAt())
                .build();
    }
}
