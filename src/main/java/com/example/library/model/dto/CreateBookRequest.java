package com.example.library.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateBookRequest {
    @NotBlank(message = "ISBN must not be blank")
    private String isbn;
    @NotBlank(message = "Title must not be blank")
    private String title;
}
