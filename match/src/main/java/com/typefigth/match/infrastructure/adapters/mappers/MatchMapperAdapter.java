package com.typefigth.match.infrastructure.adapters.mappers;

import com.typefigth.match.application.dtos.match.MatchDto;
import com.typefigth.match.domain.models.Match;
import com.typefigth.match.domain.models.Quote;
import com.typefigth.match.domain.models.User;
import com.typefigth.match.infrastructure.entities.match.MatchEntity;

import java.util.List;

public class MatchMapperAdapter implements MatchMapper {

    public MatchEntity toEntity(Match match) {
        return new MatchEntity.MatchBuilder()
                .setId(match.getId())
                .setCreatedAt(match.getCreatedAt())
                .setUpdatedAt(match.getUpdatedAt())
                .setOwnId(match.getOwnId())
                .setOpponentId(match.getOpponentId())
                .setStatus(match.getStatus())
                .build();

    }

    public Match fromEntity(MatchEntity entity) {
        return new Match.MatchBuilder()
                .setId(entity.getId())
                .setCreatedAt(entity.getCreatedAt())
                .setUpdatedAt(entity.getUpdatedAt())
                .setOwnId(entity.getOwnId())
                .setOpponentId(entity.getOpponentId())
                .setStatus(entity.getStatus())
                .build();
    }

    @Override
    public MatchDto toDto(Match match, List<User> users, List<Quote> quotes) {
        return new MatchDto.MatchBuilder()
                .setUsers(users)
                .setQuotes(quotes)
                .setId(match.getId())
                .setCreatedAt(match.getCreatedAt())
                .setUpdatedAt(match.getUpdatedAt())
                .setOwnId(match.getOwnId())
                .setOpponentId(match.getOpponentId())
                .setStatus(match.getStatus())
                .build();
    }
}
