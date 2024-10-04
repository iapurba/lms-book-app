package com.iapurba.bookapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private Long id;

    @NotBlank(message = "Author name is mandatory")
    private String name;

    private String biography;
}
