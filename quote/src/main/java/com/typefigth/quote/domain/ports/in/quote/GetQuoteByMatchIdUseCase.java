package com.typefigth.quote.domain.ports.in.quote;


import com.typefigth.quote.domain.models.quote.Quote;

import java.util.List;

public interface GetQuoteByMatchIdUseCase {

    List<Quote> getQuoteByMatchId(String matchId);

}
