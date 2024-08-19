package com.typefigth.quote.domain.ports.out.quote;


import com.typefigth.quote.domain.models.quote.Quote;

import java.util.List;
import java.util.Optional;

public interface QuoteRepositoryPort {
    List<Quote> findByMatchId(String matchId);

    Quote save(Quote quote);
}
