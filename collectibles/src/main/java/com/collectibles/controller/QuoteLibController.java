package com.collectibles.controller;

import com.collectibles.domain.dto.QuoteLibDto;
import com.collectibles.service.QuoteLibService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/quote")
@RequiredArgsConstructor
public class QuoteLibController {

    private final QuoteLibService quoteLibService;

    @GetMapping
    public List<QuoteLibDto> getRandomQuote() {
        return quoteLibService.fetchRandomQuote();
    }
}