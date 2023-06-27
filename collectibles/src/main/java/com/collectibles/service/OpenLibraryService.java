package com.collectibles.service;

import com.collectibles.domain.dto.ResultDto;
import com.collectibles.openlibrary.client.OpenLibraryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenLibraryService {

    private final OpenLibraryClient openLibraryClient;

    public ResultDto fetchBookByAuthor(String keyword) {
        return openLibraryClient.getBookByAuthor(keyword);
    }

    public ResultDto fetchBookByTitle(String keyword) {
        return openLibraryClient.getBookByTitle(keyword);
    }
}