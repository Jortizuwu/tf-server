package com.typefigth.quote.infrastructure.adapters.quote.mapper;


import com.typefigth.quote.application.dtos.quote.QuoteDto;
import com.typefigth.quote.domain.models.quote.Quote;
import com.typefigth.quote.infrastructure.entities.quote.QuoteEntity;

public class QuoteMapperAdapter implements QuoteMapper {


    @Override
    public QuoteEntity toEntity(Quote quote) {
        return new QuoteEntity(quote.getId(), quote.getQuoteId(), quote.getMatchId(), quote.getContent(), quote.getAuthor(), quote.getLength());
    }


    @Override
    public Quote fromEntity(QuoteEntity entity) {
        return new Quote(entity.getId(), entity.getQuoteId(), entity.getMatchId(), entity.getContent(), entity.getAuthor(), entity.getLength());
    }


    @Override
    public QuoteDto fromQuote(Quote quote) {
        return new QuoteDto(quote.getId(), quote.getQuoteId(), quote.getMatchId(), quote.getContent(), quote.getAuthor(), quote.getLength());
    }


}
