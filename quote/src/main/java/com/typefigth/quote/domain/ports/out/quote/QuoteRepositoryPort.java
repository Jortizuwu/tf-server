package com.typefigth.quote.domain.ports.out.quote;


import com.typefigth.quote.domain.models.quote.Quote;

import java.util.List;
import java.util.Optional;

public interface QuoteRepositoryPort {
    Optional<Quote> findById(String id);

    List<Quote> findAll();

    Quote save(Quote quote);
}
