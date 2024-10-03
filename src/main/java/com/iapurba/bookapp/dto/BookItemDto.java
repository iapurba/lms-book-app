package com.iapurba.bookapp.dto;

import com.iapurba.bookapp.model.enums.BookFormat;
import com.iapurba.bookapp.model.enums.BookStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookItemDto {
    private Long id;

    @NotNull(message = "Book ID is mandatory")
    private Long bookId;

    private String barcode;
    private LocalDate borrowed;
    private LocalDate dueDate;
    private BigDecimal price;

    @NotNull(message = "Book format is mandatory")
    private BookFormat bookFormat;

    @NotNull(message = "Book status is mandatory")
    private BookStatus bookStatus;

    private LocalDate purchaseDate;
    private LocalDate publicationDate;

    private Long rackId;
}
