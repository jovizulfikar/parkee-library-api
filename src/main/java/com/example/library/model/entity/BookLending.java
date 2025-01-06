package com.example.library.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_lending")
public class BookLending {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "book_id")
    private String bookId;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false, updatable = false, insertable = false)
    private Book book;

    @Column(name = "member_id")
    private String memberId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false, updatable = false, insertable = false)
    private Member member;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "return_date")
    private LocalDate returnDate;
}
