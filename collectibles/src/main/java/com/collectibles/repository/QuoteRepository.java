package com.collectibles.repository;

import com.collectibles.domain.Quote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface QuoteRepository extends CrudRepository<Quote, Long> {

    @Override
    List<Quote> findAll();

    @Override
    Quote save(Quote quote);

    @Override
    Optional<Quote> findById(Long id);
}