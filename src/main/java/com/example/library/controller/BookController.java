package com.example.library.controller;

import com.example.library.model.dto.CreateBookRequest;
import com.example.library.model.dto.CreateBookResponse;
import com.example.library.model.dto.GetBooksResponse;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<GetBooksResponse> getBooks() {
        var response = bookService.getBooks();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateBookResponse> postBooks(
            @RequestBody @Validated CreateBookRequest request
    ) {
        var response = bookService.createBook(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
