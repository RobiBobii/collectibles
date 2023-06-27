package com.collectibles.repository;

import com.collectibles.domain.Quote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class QuoteRepositoryTest {

    @Autowired
    QuoteRepository quoteRepository;

    @Test
    void testQuoteRepositoryFindAll() {
        //Given
        Quote quote = new Quote();

        //When
        quoteRepository.save(quote);
        Long id = quote.getId();
        List<Quote> quotes = quoteRepository.findAll();

        //Then
        assertNotNull(quotes);
        assertNotEquals(0, quotes.size());

        //Cleanup
        quoteRepository.deleteById(id);
    }
}