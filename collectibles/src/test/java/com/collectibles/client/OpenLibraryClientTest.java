package com.collectibles.client;

import com.collectibles.openlibrary.config.OpenLibraryConfig;
import com.collectibles.domain.dto.ResultBookDto;
import com.collectibles.domain.dto.ResultDto;
import com.collectibles.openlibrary.client.OpenLibraryClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OpenLibraryClientTest {

    @InjectMocks
    private OpenLibraryClient openLibraryClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private OpenLibraryConfig openLibraryConfig;

    @Test
    void shouldGetBookByAuthor() throws URISyntaxException {
        //Given
        when(openLibraryConfig.getOpenLibraryApiEndpoint()).thenReturn("http://test.com/search");

        ResultBookDto resultBookDto = new ResultBookDto("test_title", new ArrayList<>(), 2000);
        ResultDto resultDto = new ResultDto(List.of(resultBookDto));

        URI url = new URI("http://test.com/search?author=test&fields=title,author_name,first_publish_year&limit=20");

        when(restTemplate.getForObject(url, ResultDto.class)).thenReturn(resultDto);

        //When
        ResultDto fetchedResult = openLibraryClient.getBookByAuthor("test");

        //Then
        assertEquals(1, fetchedResult.getBooks().size());
        assertEquals("test_title", fetchedResult.getBooks().get(0).getTitle());
        assertEquals(new ArrayList<>(), fetchedResult.getBooks().get(0).getAuthors());
    }

    @Test
    void shouldGetBookByTitle() throws URISyntaxException {
        //Given
        when(openLibraryConfig.getOpenLibraryApiEndpoint()).thenReturn("http://test.com/search");

        ResultBookDto resultBookDto = new ResultBookDto("test_title", new ArrayList<>(), 2000);
        ResultDto resultDto = new ResultDto(List.of(resultBookDto));

        URI url = new URI("http://test.com/search?title=test&fields=title,author_name,first_publish_year&limit=20");

        when(restTemplate.getForObject(url, ResultDto.class)).thenReturn(resultDto);

        //When
        ResultDto fetchedResult = openLibraryClient.getBookByTitle("test");

        //Then
        assertEquals(1, fetchedResult.getBooks().size());
        assertEquals("test_title", fetchedResult.getBooks().get(0).getTitle());
        assertEquals(new ArrayList<>(), fetchedResult.getBooks().get(0).getAuthors());
    }

    @Test
    void shouldGetEmptyResult_searchByAuthor() throws URISyntaxException {
        //Given
        when(openLibraryConfig.getOpenLibraryApiEndpoint()).thenReturn("http://test.com/search");

        URI url = new URI("http://test.com/search?author=test&fields=title,author_name,first_publish_year&limit=20");

        when(restTemplate.getForObject(url, ResultDto.class)).thenReturn(null);

        //When
        ResultDto fetchedResult = openLibraryClient.getBookByAuthor("test");

        //Then
        assertEquals(0, fetchedResult.getBooks().size());
    }

    @Test
    void shouldGetEmptyResult_searchByTitle() throws URISyntaxException {
        //Given
        when(openLibraryConfig.getOpenLibraryApiEndpoint()).thenReturn("http://test.com/search");

        URI url = new URI("http://test.com/search?title=test&fields=title,author_name,first_publish_year&limit=20");

        when(restTemplate.getForObject(url, ResultDto.class)).thenReturn(null);

        //When
        ResultDto fetchedResult = openLibraryClient.getBookByTitle("test");

        //Then
        assertEquals(0, fetchedResult.getBooks().size());
    }
}