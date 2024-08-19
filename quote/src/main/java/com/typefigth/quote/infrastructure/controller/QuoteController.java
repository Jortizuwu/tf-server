package com.typefigth.quote.infrastructure.controller;

import com.typefigth.quote.application.dtos.quote.CreateQuoteDto;
import com.typefigth.quote.application.dtos.quote.QuoteDto;
import com.typefigth.quote.application.services.quote.ExternalService;
import com.typefigth.quote.application.services.quote.QuoteService;
import com.typefigth.quote.domain.models.quote.Quote;
import com.typefigth.quote.infrastructure.adapters.quote.mapper.QuoteMapper;
import com.typefigth.quote.infrastructure.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private Logger logger = LoggerFactory.getLogger(QuoteController.class);

    private final ExternalService externalService;
    private final QuoteService quoteServices;
    private final QuoteMapper quoteMapper;

    private static final String USER_NOT_FOUND = "Quote with id %s not found";

    public QuoteController(QuoteService quoteServices, QuoteMapper quoteMapper, ExternalService externalService) {
        this.quoteServices = quoteServices;
        this.quoteMapper = quoteMapper;
        this.externalService = externalService;
    }

    @Transactional(readOnly = true)
    @GetMapping()
    public ResponseEntity<List<QuoteDto>> listQuotes() {

        List<Quote> quotes = this.quoteServices.listQuotes();

        List<QuoteDto> quotesDto = quotes.stream().map(quoteMapper::fromQuote).toList();

        return ResponseEntity.status(HttpStatus.OK).body(Arrays.stream(this.externalService.getRandomQuote().block()).toList().stream().map(quoteMapper::fromQuote).toList());
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<QuoteDto> getQuote(@PathVariable String id) {
        Quote quote = this.quoteServices.getQuote(id).orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND, id)));

        QuoteDto quoteDto = quoteMapper.fromQuote(quote);

        return ResponseEntity.status(HttpStatus.OK).body(quoteDto);
    }

    @Transactional()
    @PostMapping
    public ResponseEntity<QuoteDto> createQuote(@Valid @RequestBody CreateQuoteDto body) {

        Quote quote = new Quote();

        quote.setQuoteId(body.getMatchId());

        return ResponseEntity.status(HttpStatus.OK).body(quoteMapper.fromQuote(this.quoteServices.createQuote(quote)));
    }


}