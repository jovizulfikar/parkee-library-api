package com.example.library.service;

import com.example.library.exception.AppException;
import com.example.library.exception.Error;
import com.example.library.model.dto.*;
import com.example.library.model.entity.BookLending;
import com.example.library.repository.BookLendingRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.MemberRepository;
import com.github.ksuid.Ksuid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BookLendingService {

    private final BookLendingRepository bookLendingRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public LendBookResponse lend(LendBookRequest request) {
        var book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new AppException(Error.BOOK_NOT_REGISTERED));

        var member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new AppException(Error.MEMBER_NOT_REGISTERED));

        var bookLending = BookLending.builder()
                .id(Ksuid.newKsuid().toString())
                .bookId(book.getId())
                .memberId(member.getId())
                .dueDate(request.getDueDate())
                .build();

        bookLendingRepository.save(bookLending);

        return LendBookResponse.builder()
                .id(bookLending.getId())
                .build();
    }

    public ReturnBookResponse returnBook(ReturnBookRequest request) {
        var bookLending = bookLendingRepository.findById(request.getBookLendingId())
                .orElseThrow(() -> new AppException(Error.BOOK_LENDING_NOT_FOUND));

        bookLending.setReturnDate(LocalDate.now());
        bookLendingRepository.save(bookLending);

        return ReturnBookResponse.builder()
                .bookLendingId(bookLending.getId())
                .build();

    }

    public GetBookLendingListResponse getBookLendingList() {
        var sort = Sort.by(Sort.Direction.DESC, "id");
        var bookLendingList = bookLendingRepository.findAll(sort).stream()
                .map(GetBookLendingListResponse.BookLendingDto::from)
                .toList();

        return GetBookLendingListResponse.builder()
                .bookLendingList(bookLendingList)
                .build();
    }
}
