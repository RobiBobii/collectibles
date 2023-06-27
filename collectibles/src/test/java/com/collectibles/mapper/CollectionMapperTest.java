package com.collectibles.mapper;

import com.collectibles.domain.Collection;
import com.collectibles.domain.dto.CollectionDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CollectionMapperTest {

    @Autowired
    private CollectionMapper collectionMapper;

    @Test
    void shouldMapToCollection() {
        //Given
        CollectionDto collectionDto = new CollectionDto(1L, "name");

        //When
        Collection collection = collectionMapper.mapToCollection(collectionDto);

        //Then
        assertEquals(collection.getId(), collection.getId());
        assertEquals(collection.getName(), collection.getName());
    }

    @Test
    void shouldMapToCollectionDto() {
        //Given
        Collection collection = new Collection(1L, "name");

        //When
        CollectionDto collectionDto = collectionMapper.mapToCollectionDto(collection);

        //Then
        assertEquals(collection.getId(), collectionDto.getId());
        assertEquals(collection.getName(), collectionDto.getName());
    }

    @Test
    void shouldMapToCollectionDtoList() {
        //Given
        Collection collection = new Collection(1L, "name");
        List<Collection> collections = List.of(collection);

        //When
        List<CollectionDto> collectionDtos = collectionMapper.mapToCollectionDtoList(collections);

        //Then
        assertEquals(collections.size(), collectionDtos.size());
        assertEquals(collections.get(0).getId(), collectionDtos.get(0).getId());
        assertEquals(collections.get(0).getName(), collectionDtos.get(0).getName());
    }
}