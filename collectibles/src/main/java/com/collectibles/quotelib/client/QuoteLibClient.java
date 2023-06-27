package com.collectibles.quotelib.client;

import com.collectibles.domain.dto.QuoteLibDto;
import com.collectibles.quotelib.config.QuoteLibConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.*;

@Component
@RequiredArgsConstructor
public class QuoteLibClient {

    private final RestTemplate restTemplate;
    private final QuoteLibConfig quoteLibConfig;

    public List<QuoteLibDto> getRandomQuote() {

        final HttpHeaders headers = new HttpHeaders();
        headers.add(quoteLibConfig.getQuoteLibHeaderHost(), quoteLibConfig.getQuoteLibHost());
        headers.add(quoteLibConfig.getQuoteLibHeaderKey(), quoteLibConfig.getQuoteLibKey());

        final HttpEntity<String> entity = new HttpEntity<>(headers);

        QuoteLibDto[] response = restTemplate.exchange(quoteLibConfig.getQuoteLibApiEndpoint(), HttpMethod.GET, entity, QuoteLibDto[].class).getBody();

        try {
            return Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }
}