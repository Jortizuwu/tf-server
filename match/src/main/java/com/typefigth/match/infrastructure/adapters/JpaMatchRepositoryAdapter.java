package com.typefigth.match.infrastructure.adapters;


import com.typefigth.match.domain.models.Match;
import com.typefigth.match.domain.models.enun.Status;
import com.typefigth.match.domain.ports.out.match.MatchRepositoryPort;
import com.typefigth.match.infrastructure.adapters.mappers.MatchMapper;
import com.typefigth.match.infrastructure.entities.match.MatchEntity;
import com.typefigth.match.infrastructure.repository.JpaMatchRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaMatchRepositoryAdapter implements MatchRepositoryPort {

    private final JpaMatchRepository jpaMatchRepository;

    private final MatchMapper matchMapper;

    public JpaMatchRepositoryAdapter(JpaMatchRepository jpaMatchRepository, MatchMapper matchMapper) {
        this.jpaMatchRepository = jpaMatchRepository;
        this.matchMapper = matchMapper;
    }

    @Override
    public Optional<Match> findById(String id) {
        Optional<MatchEntity> optionalMatchEntity = this.jpaMatchRepository.findById(id);

        if (optionalMatchEntity.isEmpty()) {
            return Optional.empty();
        }

        Match match = this.matchMapper.fromEntity(optionalMatchEntity.get());
        return Optional.of(match);
    }

    @Override
    public List<Match> listMatch() {
        return this.jpaMatchRepository.findAll().stream().map(matchMapper::fromEntity).toList();
    }

    @Override
    public Match createMatch(Match match) {
        return this.matchMapper.fromEntity(this.jpaMatchRepository.save(matchMapper.toEntity(match)));
    }

    @Override
    public Match assignOpponentToMatch(Match match, String opponentId) {
        match.setOpponentId(opponentId);
        match.setStatus(Status.CURRENT);
        return this.matchMapper.fromEntity(this.jpaMatchRepository.save(this.matchMapper.toEntity(match)));
    }

    @Override
    public Match cancelMatch(Match match) {
        match.setStatus(Status.CANCELED);
        return this.matchMapper.fromEntity(this.jpaMatchRepository.save(this.matchMapper.toEntity(match)));
    }

    @Override
    public Match finishMatch(Match match) {
        match.setStatus(Status.FINISHED);
        return this.matchMapper.fromEntity(this.jpaMatchRepository.save(this.matchMapper.toEntity(match)));
    }
}
