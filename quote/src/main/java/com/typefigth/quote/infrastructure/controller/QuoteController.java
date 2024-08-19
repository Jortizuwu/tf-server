package com.typefigth.quote.infrastructure.controller;

import com.typefigth.quote.application.dtos.quote.CreateQuoteDto;
import com.typefigth.quote.application.dtos.quote.ExternalQuoteDto;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final Logger logger = LoggerFactory.getLogger(QuoteController.class);

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

        return ResponseEntity.status(HttpStatus.OK).body(quotesDto);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<QuoteDto> getQuote(@PathVariable String id) {
        Quote quote = this.quoteServices.getQuote(id).orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND, id)));

        QuoteDto quoteDto = quoteMapper.fromQuote(quote);

        return ResponseEntity.status(HttpStatus.OK).body(quoteDto);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<QuoteDto> createQuote(@Valid @RequestBody CreateQuoteDto body) {
        try {
            externalService.getMatchByID(body.getMatchId()).doOnError(throwable -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Match with id " + body.getMatchId() + " not found");
            }).block();

            ExternalQuoteDto[] quotes = externalService.getRandomQuote().doOnError(throwable -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "quotes not found");
            }).block();

            if (quotes == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "quotes not found");
            }

            if (quotes.length == 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "quotes not found");
            }
            
            for (ExternalQuoteDto quote : quotes) {
                Quote newQuote = new Quote();
                newQuote.setAuthor(quote.getAuthor());
                newQuote.setContent(quote.getContent());
                newQuote.setLength(quote.getContent().length());
                newQuote.setMatchId(body.getMatchId());
                this.quoteServices.createQuote(newQuote);
            }

            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}