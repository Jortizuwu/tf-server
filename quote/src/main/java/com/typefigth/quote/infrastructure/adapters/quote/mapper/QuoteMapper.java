package com.typefigth.quote.infrastructure.adapters.quote.mapper;


import com.typefigth.quote.application.dtos.quote.QuoteDto;
import com.typefigth.quote.domain.models.quote.Quote;
import com.typefigth.quote.infrastructure.entities.quote.QuoteEntity;

public interface QuoteMapper {

    QuoteEntity toEntity(Quote quote);

    Quote fromEntity(QuoteEntity entity);

    QuoteDto fromQuote(Quote quote);
}
