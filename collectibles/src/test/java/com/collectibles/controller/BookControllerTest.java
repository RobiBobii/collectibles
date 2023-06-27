package com.collectibles.controller;

import com.collectibles.domain.Book;
import com.collectibles.domain.dto.BookDto;
import com.collectibles.mapper.BookMapper;
import com.collectibles.service.BookService;
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
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private BookMapper bookMapper;

    @Test
    void shouldGetBooks() throws Exception {
        //Given
        List<Book> books = List.of(Book.builder()
                .id(1L)
                .title("title")
                .author("author")
                .year("year")
                .note("note")
                .build());
        List<BookDto> bookDtos = List.of(BookDto.builder()
                .id(1L)
                .title("title")
                .author("author")
                .year("year")
                .note("note")
                .build());

        when(bookService.getAllBooks()).thenReturn(books);
        when(bookMapper.mapToBookDtoList(books)).thenReturn(bookDtos);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.status()).is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author", Matchers.is("author")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].year", Matchers.is("year")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].note", Matchers.is("note")));
    }

    @Test
    void shouldGetBook() throws Exception {
        //Given
        Book book = Book.builder()
                .id(1L)
                .title("title")
                .author("author")
                .year("year")
                .note("note")
                .build();
        BookDto bookDto = BookDto.builder()
                .id(1L)
                .title("title")
                .author("author")
                .year("year")
                .note("note")
                .build();

        when(bookService.getBook(book.getId())).thenReturn(book);
        when(bookMapper.mapToBookDto(book)).thenReturn(bookDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.status()).is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", Matchers.is("author")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year", Matchers.is("year")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.note", Matchers.is("note")));
    }

    @Test
    void shouldDeleteBook() throws Exception {
        //Given
        doNothing().when(bookService).deleteBook(anyLong());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateBook() throws Exception {
        //Given
        Book book = Book.builder()
                .id(1L)
                .title("title")
                .author("author")
                .year("year")
                .note("note")
                .build();
        BookDto bookDto = BookDto.builder()
                .id(1L)
                .title("title")
                .author("author")
                .year("year")
                .note("note")
                .build();

        when(bookMapper.mapToBook(any(BookDto.class))).thenReturn(book);
        when(bookService.saveBook(book)).thenReturn(book);
        when(bookMapper.mapToBookDto(book)).thenReturn(bookDto);

        Gson gson = new Gson();
        String json = gson.toJson(bookDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect((MockMvcResultMatchers.status()).is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", Matchers.is("author")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year", Matchers.is("year")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.note", Matchers.is("note")));
    }

    @Test
    void shouldCreateBook() throws Exception {
        //Given
        Book book = Book.builder()
                .id(1L)
                .title("title")
                .author("author")
                .year("year")
                .note("note")
                .build();
        BookDto bookDto = BookDto.builder()
                .id(1L)
                .title("title")
                .author("author")
                .year("year")
                .note("note")
                .build();

        when(bookMapper.mapToBook(bookDto)).thenReturn(book);
        when(bookService.saveBook(book)).thenReturn(book);

        Gson gson = new Gson();
        String json = gson.toJson(bookDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect((MockMvcResultMatchers.status()).is(200));
    }
}