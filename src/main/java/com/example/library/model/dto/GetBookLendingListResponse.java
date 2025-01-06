package com.example.library.model.dto;

import com.example.library.model.entity.Book;
import com.example.library.model.entity.BookLending;
import com.example.library.model.entity.Member;
import lombok.Builder;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Getter
public class GetBookLendingListResponse {

    @Builder.Default
    private List<BookLendingDto> bookLendingList = new ArrayList<>();

    @Builder
    @Getter
    public static class BookLendingDto {
        private String id;
        private MemberDto member;
        private BookDto book;
        private LocalDate dueDate;
        private LocalDate returnDate;
        private Boolean isLateReturn;

        public static BookLendingDto from(BookLending bookLending) {
            var isLateReturn = Objects.nonNull(bookLending.getReturnDate())
                    ? bookLending.getReturnDate().isAfter(bookLending.getDueDate())
                    : LocalDate.now().isAfter(bookLending.getDueDate());

            return BookLendingDto.builder()
                    .id(bookLending.getId())
                    .book(BookDto.from(bookLending.getBook()))
                    .member(MemberDto.from(bookLending.getMember()))
                    .dueDate(bookLending.getDueDate())
                    .returnDate(bookLending.getReturnDate())
                    .isLateReturn(isLateReturn)
                    .build();
        }

        @Builder
        @Getter
        public static class BookDto {
            private String id;
            private String title;

            public static BookDto from(Book book) {
                return BookDto.builder()
                        .id(book.getId())
                        .title(builder().title)
                        .build();
            }

        }

        @Builder
        @Getter
        public static class MemberDto {
            private String id;
            private String name;

            public static MemberDto from(Member member) {
                return MemberDto.builder()
                        .id(member.getId())
                        .name(member.getName())
                        .build();
            }
        }
    }
}
