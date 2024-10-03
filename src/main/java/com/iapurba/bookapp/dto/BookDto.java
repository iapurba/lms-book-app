package com.iapurba.bookapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;

    @NotBlank(message = "ISBN is mandatory")
    private String isbn;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Subject is mandatory")
    private String subject;

    private String description;

    @NotBlank(message = "Publisher is mandatory")
    private String publisher;

    @NotNull(message = "Number of pages is mandatory")
    private int numOfPages;

    private String language;

    @Size(min = 1, message = "At least one author must be provided")
    @NotNull(message = "Authors list is mandatory")
    private List<AuthorDto> authors;
}
