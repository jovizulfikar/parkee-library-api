package com.example.library.service;

import com.example.library.exception.AppException;
import com.example.library.exception.Error;
import com.example.library.model.dto.CreateBookRequest;
import com.example.library.model.dto.CreateBookResponse;
import com.example.library.model.dto.GetBooksResponse;
import com.example.library.model.entity.Book;
import com.example.library.repository.BookRepository;
import com.github.ksuid.Ksuid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public GetBooksResponse getBooks() {
        var books = bookRepository.findAll().stream()
                .map(GetBooksResponse.BookDto::from)
                .toList();

        return GetBooksResponse.builder()
                .books(books)
                .build();
    }

    public CreateBookResponse createBook(CreateBookRequest request) {
        var book = bookRepository.findByIsbn(request.getIsbn())
                .orElse(null);

        if (Objects.nonNull(book)) {
            throw new AppException(Error.BOOK_ISBN_REGISTERED);
        }

        book = Book.builder()
                .id(Ksuid.newKsuid().toString())
                .isbn(request.getIsbn())
                .title(request.getTitle())
                .build();

        bookRepository.save(book);

        return CreateBookResponse.builder()
                .id(book.getId())
                .build();
    }
}
