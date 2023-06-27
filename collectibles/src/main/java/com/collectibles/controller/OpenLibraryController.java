package com.collectibles.controller;

import com.collectibles.domain.dto.ResultBookDto;
import com.collectibles.domain.dto.ResultDto;
import com.collectibles.service.OpenLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/search")
@RequiredArgsConstructor
public class OpenLibraryController {

    private final OpenLibraryService openLibraryService;

    @GetMapping(value = "/author/{keyword}")
    public List<ResultBookDto> getBookByAuthor(@PathVariable String keyword) {
        ResultDto resultDto = openLibraryService.fetchBookByAuthor(keyword);

        List<ResultBookDto> resultBookDtoList = new ArrayList<>();
        resultBookDtoList.addAll(resultDto.getBooks());

        return resultBookDtoList;
    }

    @GetMapping(value = "/title/{keyword}")
    public List<ResultBookDto> getBookByTitle(@PathVariable String keyword) {
        ResultDto resultDto = openLibraryService.fetchBookByTitle(keyword);

        List<ResultBookDto> resultBookDtoList = new ArrayList<>();
        resultBookDtoList.addAll(resultDto.getBooks());

        return resultBookDtoList;
    }
}