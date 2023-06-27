package com.collectibles.controller;

import com.collectibles.domain.dto.QuoteLibDto;
import com.collectibles.service.QuoteLibService;
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

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(QuoteLibController.class)
class QuoteLibControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuoteLibService quoteLibService;

    @Test
    void shouldFetchEmptyList() throws Exception {
        //Given
        when(quoteLibService.fetchRandomQuote()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/quote")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.status()).is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchRandomQuote() throws Exception {
        //Given
        List<QuoteLibDto> quotes = List.of(new QuoteLibDto("quote_text", "author"));
        when(quoteLibService.fetchRandomQuote()).thenReturn(quotes);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/quote")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.status().isOk()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].quote_text", Matchers.is("quote_text")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author", Matchers.is("author")));
    }
}