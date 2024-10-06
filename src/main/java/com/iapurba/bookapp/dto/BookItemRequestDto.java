package com.iapurba.bookapp.dto;

import com.iapurba.bookapp.model.enums.BookFormat;
import com.iapurba.bookapp.model.enums.BookStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookItemRequestDto {
    private Long id;

    @NotNull(message = "Book ID is mandatory")
    private Long bookId;

    private String barcode;
    private LocalDate borrowed;
    private LocalDate dueDate;

    @NotNull(message = "Book ID is mandatory")
    private BigDecimal price;

    @NotNull(message = "Book format is mandatory")
    private BookFormat bookFormat;

    @NotNull(message = "Book status is mandatory")
    private BookStatus bookStatus;

    private LocalDate purchaseDate;
    private LocalDate publicationDate;

    private Long rackId;
}
