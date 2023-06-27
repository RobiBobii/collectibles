package com.collectibles.service;

import com.collectibles.domain.Quote;
import com.collectibles.exceptions.QuoteNotFoundException;
import com.collectibles.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;

    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    public Quote getQuote(final Long id) throws QuoteNotFoundException {
        return quoteRepository.findById(id).orElseThrow(QuoteNotFoundException::new);
    }

    public Quote saveQuote(final Quote quote) {
        return quoteRepository.save(quote);
    }

    public void deleteQuote(final Long id) throws QuoteNotFoundException {
        try {
            quoteRepository.deleteById(id);
        } catch (Exception e) {
            throw new QuoteNotFoundException();
        }
    }
}