package com.typefigth.quote.application.services.quote;


import com.typefigth.quote.domain.models.quote.Quote;
import com.typefigth.quote.domain.ports.in.quote.CreateQuoteUseCase;
import com.typefigth.quote.domain.ports.in.quote.GetQuoteUseCase;
import com.typefigth.quote.domain.ports.in.quote.ListQuotesUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuoteService implements CreateQuoteUseCase, GetQuoteUseCase, ListQuotesUseCase {
    private final CreateQuoteUseCase createQuoteUseCase;

    private final GetQuoteUseCase getQuoteUseCase;

    private final ListQuotesUseCase listQuoteUseCase;


    public QuoteService(CreateQuoteUseCase createQuoteUseCase, GetQuoteUseCase getQuoteUseCase, ListQuotesUseCase listQuoteUseCase) {
        this.createQuoteUseCase = createQuoteUseCase;
        this.getQuoteUseCase = getQuoteUseCase;
        this.listQuoteUseCase = listQuoteUseCase;
    }

    @Override
    public Quote createQuote(Quote body) {
        return this.createQuoteUseCase.createQuote(body);
    }

    @Override
    public Optional<Quote> getQuote(String id) {
        return this.getQuoteUseCase.getQuote(id);
    }

    @Override
    public List<Quote> listQuotes() {
        return this.listQuoteUseCase.listQuotes();
    }

}


