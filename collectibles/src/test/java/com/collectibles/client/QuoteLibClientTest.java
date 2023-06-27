package com.collectibles.client;

import com.collectibles.quotelib.config.QuoteLibConfig;
import com.collectibles.domain.dto.QuoteLibDto;
import com.collectibles.quotelib.client.QuoteLibClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuoteLibClientTest {

    @InjectMocks
    private QuoteLibClient quoteLibClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private QuoteLibConfig quoteLibConfig;

    @Test
    void shouldGetRandomQuote() {
        //Given
        when(quoteLibConfig.getQuoteLibApiEndpoint()).thenReturn("http://test.com");
        when(quoteLibConfig.getQuoteLibHeaderHost()).thenReturn("test");
        when(quoteLibConfig.getQuoteLibHost()).thenReturn("test");
        when(quoteLibConfig.getQuoteLibHeaderKey()).thenReturn("test");
        when(quoteLibConfig.getQuoteLibKey()).thenReturn("test");

        QuoteLibDto[] quotes = new QuoteLibDto[1];
        quotes[0] = new QuoteLibDto("test_content", "test_author");

        ResponseEntity<QuoteLibDto[]> responseEntity = new ResponseEntity<>(quotes, HttpStatus.ACCEPTED);

        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<QuoteLibDto[]>>any())).thenReturn(responseEntity);

        //When
        List<QuoteLibDto> fetchedQuote = quoteLibClient.getRandomQuote();

        //Then
        assertEquals(1, fetchedQuote.size());
        assertEquals("test_content", fetchedQuote.get(0).getQuote_text());
        assertEquals("test_author", fetchedQuote.get(0).getAuthor());
    }
}