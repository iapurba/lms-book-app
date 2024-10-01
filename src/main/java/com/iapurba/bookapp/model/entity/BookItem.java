package com.iapurba.bookapp.model.entity;

import com.iapurba.bookapp.model.enums.BookFormat;
import com.iapurba.bookapp.model.enums.BookStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private String barcode;
    private boolean isReferenceOnly;
    private Date borrowed;
    private Date dueDate;
    private double price;

    @Enumerated(EnumType.STRING)
    private BookFormat bookFormat;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    private Date purchaseDate;
    private Date publicationDate;

    private Long rackId;
}
