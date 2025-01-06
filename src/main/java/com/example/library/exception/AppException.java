package com.example.library.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

    private final Error error;

    public AppException(Error error) {
        super(error.getCode());
        this.error = error;
    }
}
