package com.example.library.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReturnBookResponse {
    private String bookLendingId;
}
