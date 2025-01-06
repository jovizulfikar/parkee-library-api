package com.example.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Error {
    BOOK_ISBN_REGISTERED("/errors/books/isbn-registered"),
    BOOK_NOT_REGISTERED("/erros/books/not-registered"),

    MEMBER_ID_CARD_REGISTERED("/errors/members/id-card-registered"),
    MEMBER_NOT_REGISTERED("/erros/members/not-registered"),

    BOOK_LENDING_NOT_FOUND("/erros/book-lending/not-found");

    private final String code;
}
