package com.collectibles.repository;

import com.collectibles.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BookRepository extends CrudRepository<Book, Long> {

    @Override
    List<Book> findAll();

    @Override
    Book save(Book book);

    @Override
    Optional<Book> findById(Long id);
}