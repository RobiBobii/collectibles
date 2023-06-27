package com.collectibles.openlibrary.client;

import com.collectibles.domain.dto.ResultDto;
import com.collectibles.openlibrary.config.OpenLibraryConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OpenLibraryClient {

    private final RestTemplate restTemplate;
    private final OpenLibraryConfig openLibraryConfig;

    public ResultDto getBookByAuthor(String keyword) {
        URI url = UriComponentsBuilder.fromHttpUrl(openLibraryConfig.getOpenLibraryApiEndpoint())
                .queryParam("author", keyword)
                .queryParam("fields", "title,author_name,first_publish_year")
                .queryParam("limit", "20")
                .build().encode().toUri();

        try {
            return Optional.ofNullable(restTemplate.getForObject(url, ResultDto.class)).orElse(new ResultDto(new ArrayList<>()));
        } catch (RestClientException e) {
            return new ResultDto(new ArrayList<>());
        }
    }

    public ResultDto getBookByTitle(String keyword) {
        URI url = UriComponentsBuilder.fromHttpUrl(openLibraryConfig.getOpenLibraryApiEndpoint())
                .queryParam("title", keyword)
                .queryParam("fields", "title,author_name,first_publish_year")
                .queryParam("limit", "20")
                .build().encode().toUri();

        try {
            return Optional.ofNullable(restTemplate.getForObject(url, ResultDto.class))
                    .orElse(new ResultDto(new ArrayList<>()));
        } catch (RestClientException e) {
            return new ResultDto(new ArrayList<>());
        }
    }
}