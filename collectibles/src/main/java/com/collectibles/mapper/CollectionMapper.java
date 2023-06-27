package com.collectibles.mapper;

import com.collectibles.domain.Collection;
import com.collectibles.domain.dto.CollectionDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectionMapper {

    public Collection mapToCollection(final CollectionDto collectionDto) {
        return new Collection(
                collectionDto.getId(),
                collectionDto.getName()
        );
    }

    public CollectionDto mapToCollectionDto(final Collection collection) {
        return new CollectionDto(
                collection.getId(),
                collection.getName()
        );
    }

    public List<CollectionDto> mapToCollectionDtoList(final List<Collection> collectionList) {
        return collectionList.stream()
                .map(this::mapToCollectionDto)
                .collect(Collectors.toList());
    }
}