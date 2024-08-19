package com.typefigth.quote.application.services.quote;


import com.typefigth.quote.domain.models.quote.Quote;
import com.typefigth.quote.domain.ports.in.quote.CreateQuoteUseCase;
import com.typefigth.quote.domain.ports.in.quote.GetQuoteByMatchIdUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService implements CreateQuoteUseCase, GetQuoteByMatchIdUseCase {
    private final CreateQuoteUseCase createQuoteUseCase;

    private final GetQuoteByMatchIdUseCase getQuoteByMatchIdUseCase;


    public QuoteService(CreateQuoteUseCase createQuoteUseCase, GetQuoteByMatchIdUseCase getQuoteByMatchIdUseCase) {
        this.createQuoteUseCase = createQuoteUseCase;
        this.getQuoteByMatchIdUseCase = getQuoteByMatchIdUseCase;
    }

    @Override
    public Quote createQuote(Quote body) {
        return this.createQuoteUseCase.createQuote(body);
    }

    @Override
    public List<Quote> getQuoteByMatchId(String id) {
        return this.getQuoteByMatchIdUseCase.getQuoteByMatchId(id);
    }


}


