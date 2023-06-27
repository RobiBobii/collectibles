package com.collectibles.controller;

import com.collectibles.domain.dto.ResultBookDto;
import com.collectibles.domain.dto.ResultDto;
import com.collectibles.service.OpenLibraryService;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(OpenLibraryController.class)
class OpenLibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OpenLibraryService openLibraryService;

    @Test
    void shouldGetBookByAuthor() throws Exception {
        //Given
        ResultBookDto resultBookDto = new ResultBookDto("title", List.of("author"), 2000);
        ResultDto resultDto = new ResultDto(List.of(resultBookDto));
        when(openLibraryService.fetchBookByAuthor(anyString())).thenReturn(resultDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/search/author/keyword")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.status()).is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author_name", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author_name[0]", Matchers.is("author")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].first_publish_year", Matchers.is(2000)));
    }

    @Test
    void shouldGetBookByTitle() throws Exception {
        //Given
        ResultBookDto resultBookDto = new ResultBookDto("title", List.of("author"), 2000);
        ResultDto resultDto = new ResultDto(List.of(resultBookDto));
        when(openLibraryService.fetchBookByTitle(anyString())).thenReturn(resultDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/search/title/keyword")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.status()).is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author_name", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author_name[0]", Matchers.is("author")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].first_publish_year", Matchers.is(2000)));
    }
}