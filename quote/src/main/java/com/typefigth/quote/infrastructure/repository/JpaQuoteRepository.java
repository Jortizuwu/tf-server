package com.typefigth.quote.infrastructure.repository;

import com.typefigth.quote.infrastructure.entities.quote.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaQuoteRepository extends JpaRepository<QuoteEntity, String> {
    List<QuoteEntity> findAllByMatchId(String matchId);
}
