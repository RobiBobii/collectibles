package com.collectibles.quotelib.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class QuoteLibConfig {

    @Value("${quotelib.api.endpoint}")
    private String quoteLibApiEndpoint;

    @Value("${quotelib.header.host}")
    private String quoteLibHeaderHost;

    @Value("${quotelib.host}")
    private String quoteLibHost;

    @Value("${quotelib.header.key}")
    private String quoteLibHeaderKey;

    @Value("${quotelib.key}")
    private String quoteLibKey;
}