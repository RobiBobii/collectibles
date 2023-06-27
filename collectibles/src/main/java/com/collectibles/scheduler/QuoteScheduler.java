package com.collectibles.scheduler;

import com.collectibles.domain.Quote;
import com.collectibles.domain.QuoteLib;
import com.collectibles.mapper.QuoteLibMapper;
import com.collectibles.quotelib.facade.QuoteLibFacade;
import com.collectibles.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuoteScheduler {

    private final QuoteLibFacade quoteLibFacade;
    private final QuoteLibMapper quoteLibMapper;
    private final QuoteService quoteService;

    @Scheduled(cron = "0 0 12 * * *")
    public void updateQuotes() {
        List<QuoteLib> quoteLibList = quoteLibFacade.fetchRandomQuote();
        List<Quote> quoteList = quoteLibMapper.mapToQuoteList(quoteLibList);
        for (Quote quote : quoteList) {
            quoteService.saveQuote(quote);
        }
    }
}