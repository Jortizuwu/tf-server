package com.typefigth.quote.application.usecases.quote;

import com.typefigth.quote.domain.models.quote.Quote;
import com.typefigth.quote.domain.ports.in.quote.GetQuoteUseCase;
import com.typefigth.quote.domain.ports.out.quote.QuoteRepositoryPort;

import java.util.Optional;

public class GetQuoteUseCaseImpl implements GetQuoteUseCase {
    private final QuoteRepositoryPort quoteRepositoryPort;

    public GetQuoteUseCaseImpl(QuoteRepositoryPort quoteRepositoryPort) {
        this.quoteRepositoryPort = quoteRepositoryPort;
    }

    @Override
    public Optional<Quote> getQuote(String id) {
        return quoteRepositoryPort.findById(id);
    }
}
