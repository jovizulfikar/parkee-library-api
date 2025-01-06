package com.example.library.model.dto;

import com.example.library.model.entity.Book;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class GetBooksResponse {

    @Builder.Default
    private List<BookDto> books = new ArrayList<>();

    @Builder
    @Getter
    public static class BookDto {
        private String id;
        private String isbn;
        private String title;

        public static BookDto from(Book book) {
            return BookDto.builder()
                    .id(book.getId())
                    .isbn(book.getIsbn())
                    .title(book.getTitle())
                    .build();
        }
    }
}
