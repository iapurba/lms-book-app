package com.iapurba.bookapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private Long id;

    @NotBlank(message = "Author name is mandatory")
    private String name;

    private String bio;
}
