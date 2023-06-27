package com.collectibles.mapper;

import com.collectibles.domain.Quote;
import com.collectibles.domain.QuoteLib;
import com.collectibles.domain.dto.QuoteLibDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuoteLibMapper {

    public QuoteLib mapToQuoteLib(final QuoteLibDto quoteLibDto) {
        return new QuoteLib(quoteLibDto.getQuote_text(), quoteLibDto.getAuthor());
    }

    public List<QuoteLib> mapToQuoteLibList(final List<QuoteLibDto> quoteLibDtoList) {
        return quoteLibDtoList.stream()
                .map(this::mapToQuoteLib)
                .collect(Collectors.toList());
    }

    public Quote mapToQuote(final QuoteLib quoteLib) {
        return new Quote(quoteLib.getAuthor(), quoteLib.getQuote_text());
    }

    public List<Quote> mapToQuoteList(final List<QuoteLib> quoteLibList) {
        return quoteLibList.stream()
                .map(this::mapToQuote)
                .collect(Collectors.toList());
    }
}