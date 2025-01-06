package com.example.library.helper;

import com.example.library.exception.AppException;
import com.example.library.exception.Error;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.net.URI;
import java.util.Optional;

import static com.example.library.exception.Error.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionTranslator {

    public static ProblemDetail defaultProblemDetail() {
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong.");
        var title = Optional.of(problemDetail.getTitle()).orElse("");
        problemDetail.setType(URI.create("/errors/" + title.toLowerCase().replace(" ", "-")));
        return problemDetail;
    }

    public static ProblemDetail toProblemDetail(MethodArgumentNotValidException e) {
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, e.getFieldError().getDefaultMessage());
        problemDetail.setTitle("Validation Failed");
        problemDetail.setType(URI.create("/errors/validation"));
        return problemDetail;
    }

    public static ProblemDetail toProblemDetail(AppException e) {
        return switch (e.getError()) {
            case BOOK_ISBN_REGISTERED: {
                var problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
                problemDetail.setType(URI.create(e.getError().getCode()));
                problemDetail.setTitle("ISBN Already Registered");
                problemDetail.setDetail("The provided ISBN already registered.");
                yield problemDetail;
            }
            case BOOK_NOT_REGISTERED: {
                var problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
                problemDetail.setType(URI.create(e.getError().getCode()));
                problemDetail.setTitle("Book Not Registered");
                problemDetail.setDetail("The provided Book ID is not registered.");
                yield problemDetail;
            }
            case MEMBER_ID_CARD_REGISTERED: {
                var problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
                problemDetail.setType(URI.create(e.getError().getCode()));
                problemDetail.setTitle("ID Card Already Registered");
                problemDetail.setDetail("The provided ID Card already registered.");
                yield problemDetail;
            }
            case MEMBER_NOT_REGISTERED: {
                var problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
                problemDetail.setType(URI.create(e.getError().getCode()));
                problemDetail.setTitle("Member Not Registered");
                problemDetail.setDetail("The provided Member ID is not registered.");
                yield problemDetail;
            }
            case BOOK_LENDING_NOT_FOUND: {
                var problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
                problemDetail.setType(URI.create(e.getError().getCode()));
                problemDetail.setTitle("Book Lending Transaction Not Found");
                problemDetail.setDetail("The provided Book Lending Transaction ID is not found.");
                yield problemDetail;
            }
            default: yield ExceptionTranslator.defaultProblemDetail();
        };
    }
}
