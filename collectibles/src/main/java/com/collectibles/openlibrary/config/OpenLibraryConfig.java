package com.collectibles.openlibrary.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class OpenLibraryConfig {

    @Value("${openlibrary.api.endpoint}")
    private String openLibraryApiEndpoint;
}