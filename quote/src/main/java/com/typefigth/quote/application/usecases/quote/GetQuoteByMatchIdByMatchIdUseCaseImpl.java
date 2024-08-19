package com.typefigth.quote.application.usecases.quote;

import com.typefigth.quote.domain.models.quote.Quote;
import com.typefigth.quote.domain.ports.in.quote.GetQuoteByMatchIdUseCase;
import com.typefigth.quote.domain.ports.out.quote.QuoteRepositoryPort;

import java.util.List;
import java.util.Optional;

public class GetQuoteByMatchIdByMatchIdUseCaseImpl implements GetQuoteByMatchIdUseCase {
    private final QuoteRepositoryPort quoteRepositoryPort;

    public GetQuoteByMatchIdByMatchIdUseCaseImpl(QuoteRepositoryPort quoteRepositoryPort) {
        this.quoteRepositoryPort = quoteRepositoryPort;
    }

    @Override
    public List<Quote> getQuoteByMatchId(String matchId) {
        return quoteRepositoryPort.findByMatchId(matchId);
    }
}
