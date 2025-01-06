package com.example.library.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateMemberRequest {
    @NotBlank(message = "ID Card Number must not be blank")
    private String idCardNumber;
    @NotBlank(message = "Name must not be blank")
    private String name;
}
