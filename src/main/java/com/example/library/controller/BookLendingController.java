package com.example.library.controller;

import com.example.library.model.dto.*;
import com.example.library.service.BookLendingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book-lending")
@RequiredArgsConstructor
public class BookLendingController {

    private final BookLendingService bookLendingService;

    @PostMapping
    public ResponseEntity<LendBookResponse> postBookLending(
            @RequestBody @Validated LendBookRequest request
    ) {
        var response = bookLendingService.lend(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/return")
    public ResponseEntity<ReturnBookResponse> postReturn(
            @RequestBody @Validated ReturnBookRequest request
    ) {
        var response = bookLendingService.returnBook(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<GetBookLendingListResponse> postBookLending() {
        var response = bookLendingService.getBookLendingList();
        return ResponseEntity.ok(response);
    }
}
