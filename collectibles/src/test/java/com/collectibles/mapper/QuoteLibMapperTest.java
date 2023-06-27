package com.collectibles.mapper;

import com.collectibles.domain.Quote;
import com.collectibles.domain.QuoteLib;
import com.collectibles.domain.dto.QuoteLibDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuoteLibMapperTest {

    @Autowired
    private QuoteLibMapper quoteLibMapper;

    @Test
    void shouldMapToQuoteLib() {
        //Given
        QuoteLibDto quoteLibDto = new QuoteLibDto("quote_text", "author");

        //When
        QuoteLib quoteLib = quoteLibMapper.mapToQuoteLib(quoteLibDto);

        //Then
        assertEquals(quoteLibDto.getQuote_text(), quoteLib.getQuote_text());
        assertEquals(quoteLibDto.getAuthor(), quoteLib.getAuthor());
    }

    @Test
    void shouldMapToQuoteLibList() {
        //Given
        QuoteLibDto quoteLibDto = new QuoteLibDto("quote_text", "author");
        List<QuoteLibDto> quoteLibDtos = List.of(quoteLibDto);

        //When
        List<QuoteLib> quoteLibs = quoteLibMapper.mapToQuoteLibList(quoteLibDtos);

        //Then
        assertEquals(quoteLibDtos.size(), quoteLibs.size());
        assertEquals(quoteLibDtos.get(0).getQuote_text(), quoteLibs.get(0).getQuote_text());
        assertEquals(quoteLibDtos.get(0).getAuthor(), quoteLibs.get(0).getAuthor());
    }

    @Test
    void shouldMapToQuote() {
        //Given
        QuoteLib quoteLib = new QuoteLib("quote_text", "author");

        //When
        Quote quote = quoteLibMapper.mapToQuote(quoteLib);

        //Then
        assertEquals(quoteLib.getQuote_text(), quote.getContent());
        assertEquals(quoteLib.getAuthor(), quote.getAuthor());
    }

    @Test
    void shouldMapToQuoteList() {
        //Given
        QuoteLib quoteLib = new QuoteLib("quote_text", "author");
        List<QuoteLib> quoteLibs = List.of(quoteLib);

        //When
        List<Quote> quotes = quoteLibMapper.mapToQuoteList(quoteLibs);

        //Then
        assertEquals(quoteLibs.size(), quotes.size());
        assertEquals(quoteLibs.get(0).getQuote_text(), quotes.get(0).getContent());
        assertEquals(quoteLibs.get(0).getAuthor(), quotes.get(0).getAuthor());
    }
}