package com.collectibles.controller;

import com.collectibles.domain.Book;
import com.collectibles.domain.Collection;
import com.collectibles.domain.dto.BookDto;
import com.collectibles.domain.dto.CollectionDto;
import com.collectibles.mapper.BookMapper;
import com.collectibles.mapper.CollectionMapper;
import com.collectibles.service.BookService;
import com.collectibles.service.CollectionService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringJUnitWebConfig
@WebMvcTest(CollectionController.class)
class CollectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CollectionService collectionService;

    @MockBean
    private CollectionMapper collectionMapper;

    @MockBean
    private BookService bookService;

    @MockBean
    private BookMapper bookMapper;

    @Test
    void shouldGetCollections() throws Exception {
        //Given
        List<Collection> collections = List.of(new Collection(1L, "name"));
        List<CollectionDto> collectionDtos = List.of(new CollectionDto(1L, "name"));

        when(collectionService.getAllCollections()).thenReturn(collections);
        when(collectionMapper.mapToCollectionDtoList(collections)).thenReturn(collectionDtos);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/collections")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.status()).is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("name")));
    }

    @Test
    void shouldGetCollection() throws Exception {
        //Given
        Collection collection = new Collection(1L, "name");
        CollectionDto collectionDto = new CollectionDto(1L, "name");

        when(collectionService.getCollection(collection.getId())).thenReturn(collection);
        when(collectionMapper.mapToCollectionDto(collection)).thenReturn(collectionDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/collections/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.status()).is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("name")));
    }

    @Test
    void shouldDeleteCollection() throws Exception {
        //Given
        doNothing().when(collectionService).deleteCollection(anyLong());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/collections/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateCollection() throws Exception {
        //Given
        Collection collection = new Collection(1L, "name");
        CollectionDto collectionDto = new CollectionDto(1L, "name");

        when(collectionMapper.mapToCollection(any(CollectionDto.class))).thenReturn(collection);
        when(collectionService.saveCollection(collection)).thenReturn(collection);
        when(collectionMapper.mapToCollectionDto(collection)).thenReturn(collectionDto);

        Gson gson = new Gson();
        String json = gson.toJson(collectionDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/collections")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect((MockMvcResultMatchers.status()).is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("name")));
    }

    @Test
    void shouldCreateCollection() throws Exception {
        //Given
        Collection collection = new Collection(1L, "name");
        CollectionDto collectionDto = new CollectionDto(1L, "name");

        when(collectionMapper.mapToCollection(collectionDto)).thenReturn(collection);
        when(collectionService.saveCollection(collection)).thenReturn(collection);

        Gson gson = new Gson();
        String json = gson.toJson(collectionDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/collections")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect((MockMvcResultMatchers.status()).is(200));
    }

    @Test
    void shouldGetBooksInCollection() throws Exception {
        //Given
        List<Book> books = List.of(Book.builder()
                .id(1L)
                .title("title")
                .author("author")
                .year("year")
                .note("note")
                .build());
        Collection collection = new Collection(1L, "name", books);
        List<BookDto> bookDtos = List.of(BookDto.builder()
                .id(1L)
                .title("title")
                .author("author")
                .year("year")
                .note("note")
                .build());

        when(collectionService.getCollection(collection.getId())).thenReturn(collection);
        when(bookMapper.mapToBookDtoList(books)).thenReturn(bookDtos);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/collections/1/books")
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
    void shouldAddBookToCollection() throws Exception {
        //Given
        BookDto bookDto = BookDto.builder()
                .id(1L)
                .title("title")
                .author("author")
                .year("year")
                .note("note")
                .build();
        Collection collection = new Collection(1L, "name", new ArrayList<>());
        Book book = Book.builder()
                .id(1L)
                .title("title")
                .author("author")
                .year("year")
                .note("note")
                .build();

        when(collectionService.getCollection(collection.getId())).thenReturn(collection);
        when(bookMapper.mapToBook(any(BookDto.class))).thenReturn(book);
        when(bookService.saveBook(book)).thenReturn(book);
        when(collectionService.saveCollection(collection)).thenReturn(collection);

        Gson gson = new Gson();
        String json = gson.toJson(bookDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/collections/1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect((MockMvcResultMatchers.status()).is(200));
    }

    @Test
    void deleteBookFromCollection() throws Exception {
        //Given
        Collection collection = new Collection(1L, "name", new ArrayList<>());
        Book book = Book.builder()
                .id(1L)
                .title("title")
                .author("author")
                .year("year")
                .note("note")
                .build();

        when(collectionService.getCollection(collection.getId())).thenReturn(collection);
        when(bookService.getBook(book.getId())).thenReturn(book);
        doNothing().when(bookService).deleteBook(anyLong());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/collections/1/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}