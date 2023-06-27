package com.collectibles.repository;

import com.collectibles.domain.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CollectionRepository extends CrudRepository<Collection, Long> {

    @Override
    List<Collection> findAll();

    @Override
    Collection save(Collection collection);

    @Override
    Optional<Collection> findById(Long id);

    @Override
    void deleteById(Long id);
}