package com.iapurba.bookapp.dto;

import com.iapurba.bookapp.model.enums.BookFormat;
import com.iapurba.bookapp.model.enums.BookStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookItemDto {
    private Long id;

    @NotNull(message = "Book ID is mandatory")
    private Long bookId;

    private String barcode;
    private boolean isReferenceOnly;
    private Date borrowed;
    private Date dueDate;
    private double price;

    private BookFormat bookFormat;

    @NotBlank(message = "Book status is mandatory")
    private BookStatus bookStatus;

    private Date purchaseDate;
    private Date publicationDate;

    private Long rackId;
}
