package com.iapurba.bookapp.dto;

import com.iapurba.bookapp.model.enums.BookFormat;
import com.iapurba.bookapp.model.enums.BookStatus;
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
public class BookItemResponseDto {
    private Long id;
    private BookDto book;
    private String barcode;
    private LocalDate borrowed;
    private LocalDate dueDate;
    private BigDecimal price;
    private BookFormat bookFormat;
    private BookStatus bookStatus;
    private LocalDate purchaseDate;
    private LocalDate publicationDate;
    private Long rackId;
}
