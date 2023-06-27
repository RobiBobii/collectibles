package com.collectibles.controller;

import com.collectibles.domain.Quote;
import com.collectibles.domain.dto.QuoteDto;
import com.collectibles.exceptions.QuoteNotFoundException;
import com.collectibles.mapper.QuoteMapper;
import com.collectibles.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/quotes")
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;
    private final QuoteMapper quoteMapper;

    @GetMapping
    public ResponseEntity<List<QuoteDto>> getQuotes() {
        List<Quote> quotes = quoteService.getAllQuotes();
        return ResponseEntity.ok(quoteMapper.mapToQuoteDtoList(quotes));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<QuoteDto> getQuote(@PathVariable Long id) throws QuoteNotFoundException {
        return new ResponseEntity<>(quoteMapper.mapToQuoteDto(quoteService.getQuote(id)), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) throws QuoteNotFoundException {
        quoteService.deleteQuote(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuoteDto> updateQuote(@RequestBody QuoteDto quoteDto) {
        Quote quote = quoteMapper.mapToQuote(quoteDto);
        Quote savedQuote = quoteService.saveQuote(quote);
        return ResponseEntity.ok(quoteMapper.mapToQuoteDto(savedQuote));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createQuote(@RequestBody QuoteDto quoteDto) {
        Quote quote = quoteMapper.mapToQuote(quoteDto);
        quoteService.saveQuote(quote);
        return ResponseEntity.ok().build();
    }
}