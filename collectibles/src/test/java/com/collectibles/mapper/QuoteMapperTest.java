package com.collectibles.mapper;

import com.collectibles.domain.Quote;
import com.collectibles.domain.dto.QuoteDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuoteMapperTest {

    @Autowired
    private QuoteMapper quoteMapper;

    @Test
    void shouldMapToQuote() {
        //Given
        QuoteDto quoteDto = new QuoteDto(1L, "content", "author");

        //When
        Quote quote = quoteMapper.mapToQuote(quoteDto);

        //Then
        assertEquals(quoteDto.getId(), quote.getId());
        assertEquals(quoteDto.getContent(), quote.getContent());
        assertEquals(quoteDto.getAuthor(), quote.getAuthor());
    }

    @Test
    void shouldMapToQuoteDto() {
        //Given
        Quote quote = new Quote(1L, "content", "author");

        //When
        QuoteDto quoteDto = quoteMapper.mapToQuoteDto(quote);

        //Then
        assertEquals(quote.getId(), quoteDto.getId());
        assertEquals(quote.getContent(), quoteDto.getContent());
        assertEquals(quote.getAuthor(), quoteDto.getAuthor());
    }

    @Test
    void shouldMapToQuoteDtoList() {
        //Given
        Quote quote = new Quote(1L, "content", "author");
        List<Quote> quotes = List.of(quote);

        //When
        List<QuoteDto> quoteDtos = quoteMapper.mapToQuoteDtoList(quotes);

        //Then
        assertEquals(quotes.size(), quoteDtos.size());
        assertEquals(quotes.get(0).getId(), quoteDtos.get(0).getId());
        assertEquals(quotes.get(0).getContent(), quoteDtos.get(0).getContent());
        assertEquals(quotes.get(0).getAuthor(), quoteDtos.get(0).getAuthor());
    }
}