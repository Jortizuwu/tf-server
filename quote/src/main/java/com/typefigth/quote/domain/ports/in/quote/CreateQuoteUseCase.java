package com.typefigth.quote.domain.ports.in.quote;


import com.typefigth.quote.domain.models.quote.Quote;

public interface CreateQuoteUseCase {
    Quote createQuote(Quote quote);
}
