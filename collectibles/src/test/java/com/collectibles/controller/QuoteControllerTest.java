package com.collectibles.controller;

import com.collectibles.domain.Quote;
import com.collectibles.domain.dto.QuoteDto;
import com.collectibles.mapper.QuoteMapper;
import com.collectibles.service.QuoteService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(QuoteController.class)
class QuoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuoteService quoteService;

    @MockBean
    private QuoteMapper quoteMapper;

    @Test
    void shouldGetQuotes() throws Exception {
        //Given
        List<Quote> quotes = List.of(new Quote(1L, "author", "content"));
        List<QuoteDto> quoteDtos = List.of(new QuoteDto(1L, "author", "content"));

        when(quoteService.getAllQuotes()).thenReturn(quotes);
        when(quoteMapper.mapToQuoteDtoList(quotes)).thenReturn(quoteDtos);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/quotes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.status()).is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("content")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author", Matchers.is("author")));
    }

    @Test
    void shouldGetQuote() throws Exception {
        //Given
        Quote quote = new Quote(1L, "author", "content");
        QuoteDto quoteDto = new QuoteDto(1L, "author", "content");

        when(quoteService.getQuote(quote.getId())).thenReturn(quote);
        when(quoteMapper.mapToQuoteDto(quote)).thenReturn(quoteDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/quotes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.status()).is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("content")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", Matchers.is("author")));
    }

    @Test
    void shouldDeleteQuote() throws Exception {
        //Given
        doNothing().when(quoteService).deleteQuote(anyLong());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/quotes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateQuote() throws Exception {
        //Given
        Quote quote = new Quote(1L, "author", "content");
        QuoteDto quoteDto = new QuoteDto(1L, "author", "content");

        when(quoteMapper.mapToQuote(any(QuoteDto.class))).thenReturn(quote);
        when(quoteService.saveQuote(quote)).thenReturn(quote);
        when(quoteMapper.mapToQuoteDto(quote)).thenReturn(quoteDto);

        Gson gson = new Gson();
        String json = gson.toJson(quoteDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/quotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect((MockMvcResultMatchers.status()).is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("content")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", Matchers.is("author")));
    }

    @Test
    void shouldCreateQuote() throws Exception {
        //Given
        Quote quote = new Quote(1L, "author", "content");
        QuoteDto quoteDto = new QuoteDto(1L, "author", "content");

        when(quoteMapper.mapToQuote(quoteDto)).thenReturn(quote);
        when(quoteService.saveQuote(quote)).thenReturn(quote);

        Gson gson = new Gson();
        String json = gson.toJson(quoteDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/quotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect((MockMvcResultMatchers.status()).is(200));

    }
}