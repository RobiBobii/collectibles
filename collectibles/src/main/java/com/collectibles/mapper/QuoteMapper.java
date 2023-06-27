package com.collectibles.mapper;

import com.collectibles.domain.Quote;
import com.collectibles.domain.dto.QuoteDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuoteMapper {

    public Quote mapToQuote(final QuoteDto quoteDto) {
        return new Quote(
                quoteDto.getId(),
                quoteDto.getAuthor(),
                quoteDto.getContent()
        );
    }

    public QuoteDto mapToQuoteDto(final Quote quote) {
        return new QuoteDto(
                quote.getId(),
                quote.getAuthor(),
                quote.getContent()
        );
    }

    public List<QuoteDto> mapToQuoteDtoList(final List<Quote> quoteList) {
        return quoteList.stream()
                .map(this::mapToQuoteDto)
                .collect(Collectors.toList());
    }
}