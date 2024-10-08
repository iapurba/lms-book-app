package com.iapurba.bookapp.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.iapurba.bookapp.model.enums.BookFormat;
import com.iapurba.bookapp.model.enums.BookStatus;
import com.iapurba.bookapp.util.BarcodeUtil;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "book_items")
@Data
@NoArgsConstructor
public class BookItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @JsonBackReference
    private Book book;

    private String barcode;
    private LocalDate borrowed;
    private LocalDate dueDate;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private BookFormat bookFormat;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    private LocalDate purchaseDate;
    private LocalDate publicationDate;

    private Long rackId;

    @PrePersist
    public void generateTempBarcode() {
        this.barcode = BarcodeUtil.generateBarcode(
                0L, book.getIsbn(), publicationDate, rackId
        );
    }

    @PostPersist
    public void updateToActualBarcode() {
        this.barcode = BarcodeUtil.generateBarcode(
                this.id, book.getIsbn(), publicationDate, rackId
        );
    }
}
