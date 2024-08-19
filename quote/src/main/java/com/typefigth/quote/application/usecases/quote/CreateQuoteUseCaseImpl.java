package com.typefigth.quote.application.usecases.quote;

import com.typefigth.quote.domain.models.quote.Quote;
import com.typefigth.quote.domain.ports.in.quote.CreateQuoteUseCase;
import com.typefigth.quote.domain.ports.out.quote.QuoteRepositoryPort;

public class CreateQuoteUseCaseImpl implements CreateQuoteUseCase {

    private final QuoteRepositoryPort quoteRepositoryPort;

    public CreateQuoteUseCaseImpl(QuoteRepositoryPort quoteRepositoryPort) {
        this.quoteRepositoryPort = quoteRepositoryPort;
    }

    @Override
    public Quote createQuote(Quote quote) {
        return this.quoteRepositoryPort.save(quote);
    }
}
