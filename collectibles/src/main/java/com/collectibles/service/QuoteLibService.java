package com.collectibles.service;

import com.collectibles.domain.dto.QuoteLibDto;
import com.collectibles.quotelib.client.QuoteLibClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuoteLibService {

    private final QuoteLibClient quoteLibClient;

    public List<QuoteLibDto> fetchRandomQuote() {
        return quoteLibClient.getRandomQuote();
    }
}