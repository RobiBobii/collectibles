package com.collectibles.controller;

import com.collectibles.domain.Book;
import com.collectibles.domain.Collection;
import com.collectibles.domain.dto.BookDto;
import com.collectibles.domain.dto.CollectionDto;
import com.collectibles.exceptions.BookNotFoundException;
import com.collectibles.exceptions.CollectionNotFoundException;
import com.collectibles.mapper.BookMapper;
import com.collectibles.mapper.CollectionMapper;
import com.collectibles.service.BookService;
import com.collectibles.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/collections")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;
    private final CollectionMapper collectionMapper;
    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<CollectionDto>> getCollections() {
        List<Collection> collections = collectionService.getAllCollections();
        return ResponseEntity.ok(collectionMapper.mapToCollectionDtoList(collections));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CollectionDto> getCollection(@PathVariable Long id) throws CollectionNotFoundException {
        return new ResponseEntity<>(collectionMapper.mapToCollectionDto(collectionService.getCollection(id)), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteCollection(@PathVariable Long id) throws CollectionNotFoundException {
        collectionService.deleteCollection(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionDto> updateCollection(@RequestBody CollectionDto collectionDto) {
        Collection collection = collectionMapper.mapToCollection(collectionDto);
        Collection savedCollection = collectionService.saveCollection(collection);
        return ResponseEntity.ok(collectionMapper.mapToCollectionDto(savedCollection));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCollection(@RequestBody CollectionDto collectionDto) {
        Collection collection = collectionMapper.mapToCollection(collectionDto);
        collectionService.saveCollection(collection);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{id}/books")
    public ResponseEntity<List<BookDto>> getBooksInCollection(@PathVariable Long id) throws CollectionNotFoundException {
        List<BookDto> books = bookMapper.mapToBookDtoList(collectionService.getCollection(id).getBooks());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping(value = "{id}/books")
    public ResponseEntity<Void> addBookToCollection(@PathVariable Long id, @RequestBody BookDto bookDto) throws CollectionNotFoundException {
        Collection collection = collectionService.getCollection(id);
        Book book = bookMapper.mapToBook(bookDto);
        collection.getBooks().add(book);
        book.setCollection(collection);
        bookService.saveBook(book);
        collectionService.saveCollection(collection);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{collectionId}/books/{bookId}")
    public ResponseEntity<Void> deleteBookFromCollection(@PathVariable Long collectionId, @PathVariable Long bookId) throws CollectionNotFoundException, BookNotFoundException {
        Collection collection = collectionService.getCollection(collectionId);
        Book book = bookService.getBook(bookId);
        collection.getBooks().remove(book);
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }
}