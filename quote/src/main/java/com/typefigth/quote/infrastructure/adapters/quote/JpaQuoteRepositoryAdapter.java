package com.typefigth.quote.infrastructure.adapters.quote;


import com.typefigth.quote.domain.models.quote.Quote;
import com.typefigth.quote.domain.ports.out.quote.QuoteRepositoryPort;
import com.typefigth.quote.infrastructure.adapters.quote.mapper.QuoteMapper;
import com.typefigth.quote.infrastructure.entities.quote.QuoteEntity;
import com.typefigth.quote.infrastructure.repository.JpaQuoteRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaQuoteRepositoryAdapter implements QuoteRepositoryPort {

    private final JpaQuoteRepository jpaQuoteRepository;

    private final QuoteMapper quoteMapper;

    public JpaQuoteRepositoryAdapter(JpaQuoteRepository jpaQuoteRepository, QuoteMapper quoteMapper) {
        this.jpaQuoteRepository = jpaQuoteRepository;
        this.quoteMapper = quoteMapper;
    }

    @Override
    public List<Quote> findByMatchId(String matchId) {
        List<QuoteEntity> listQuoteEntity = this.jpaQuoteRepository.findAllByMatchId(matchId);

        if (listQuoteEntity.isEmpty()) {
            return List.of();
        }
        return listQuoteEntity.stream().map(quoteMapper::fromEntity).toList();
    }


    @Override
    public Quote save(Quote quote) {
        return this.quoteMapper.fromEntity(this.jpaQuoteRepository.save(quoteMapper.toEntity(quote)));
    }
}
