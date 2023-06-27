package com.collectibles.quotelib.facade;

import com.collectibles.domain.QuoteLib;
import com.collectibles.mapper.QuoteLibMapper;
import com.collectibles.service.QuoteLibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuoteLibFacade {

    @Autowired
    QuoteLibService quoteLibService;

    @Autowired
    QuoteLibMapper quoteLibMapper;

    public List<QuoteLib> fetchRandomQuote() {
        return quoteLibMapper.mapToQuoteLibList(quoteLibService.fetchRandomQuote());
    }
}