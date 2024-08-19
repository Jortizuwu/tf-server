package com.typefigth.quote.application.usecases.quote;


import com.typefigth.quote.domain.models.quote.Quote;
import com.typefigth.quote.domain.ports.in.quote.ListQuotesUseCase;
import com.typefigth.quote.domain.ports.out.quote.QuoteRepositoryPort;

import java.util.List;

public class ListQuotesUseCaseImpl implements ListQuotesUseCase {

    private final QuoteRepositoryPort quoteRepositoryPort;

    public ListQuotesUseCaseImpl(QuoteRepositoryPort quoteRepositoryPort) {
        this.quoteRepositoryPort = quoteRepositoryPort;
    }

    @Override
    public List<Quote> listQuotes() {
        return this.quoteRepositoryPort.findAll();
    }
}
