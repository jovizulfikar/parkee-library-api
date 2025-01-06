package com.example.library.model.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class LendBookRequest {
    @NotNull(message = "Member must not be blank")
    private String memberId;
    @NotNull(message = "Book must not be blank")
    private String bookId;
    @NotNull(message = "Due date must not be blank")
//    @Future(message = "Due date must be in the future")
    private LocalDate dueDate;
}
