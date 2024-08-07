package com.typefigth.match.infrastructure.adapters;


import com.typefigth.match.domain.models.Match;
import com.typefigth.match.domain.ports.out.match.MatchRepositoryPort;
import com.typefigth.match.infrastructure.adapters.mappers.MatchMapper;
import com.typefigth.match.infrastructure.entities.match.MatchEntity;
import com.typefigth.match.infrastructure.repository.JpaMatchRepository;

import java.util.List;
import java.util.Optional;

public class JpaMatchRepositoryAdapter implements MatchRepositoryPort {

    private JpaMatchRepository jpaMatchRepository;
    private MatchMapper matchMapper;

    public JpaMatchRepositoryAdapter(JpaMatchRepository jpaMatchRepository, MatchMapper matchMapper) {
        this.jpaMatchRepository = jpaMatchRepository;
        this.matchMapper = matchMapper;

    }

    @Override
    public Optional<Match> findById(String id) {
        Optional<MatchEntity> matchEntity = jpaMatchRepository.findById(id);

        if (matchEntity.isPresent()) {
            return Optional.of(matchMapper.fromEntity(matchEntity.get()));
        }
        return Optional.empty();
    }

    @Override
    public List<Match> listMatch() {
        return jpaMatchRepository.findAll().stream().map(matchMapper::fromEntity).toList();
    }

    @Override
    public Match createMatch(Match match) {
        return matchMapper.fromEntity(this.jpaMatchRepository.save(matchMapper.toEntity(match)));
    }
}
