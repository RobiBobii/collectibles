package com.collectibles.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuoteDto {

    private Long id;
    private String author;
    private String content;
}