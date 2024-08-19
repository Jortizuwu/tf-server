package com.typefigth.quote.domain.ports.in.quote;


import com.typefigth.quote.domain.models.quote.Quote;

import java.util.Optional;

public interface GetQuoteUseCase {

    Optional<Quote> getQuote(String id);

}
