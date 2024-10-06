package com.iapurba.bookapp.mapper;

import com.iapurba.bookapp.dto.BookItemRequestDto;
import com.iapurba.bookapp.dto.BookItemResponseDto;
import com.iapurba.bookapp.model.entity.BookItem;

public class BookItemMapper {
    private  final BookMapper bookMapper = new BookMapper();

    public BookItemRequestDto toRequestDto(BookItem bookItem) {
        if (bookItem == null) return  null;

        return BookItemRequestDto.builder()
                .id(bookItem.getId())
                .bookId(bookItem.getBook().getId())
                .barcode(bookItem.getBarcode())
                .borrowed(bookItem.getBorrowed())
                .dueDate(bookItem.getDueDate())
                .price(bookItem.getPrice())
                .bookFormat(bookItem.getBookFormat())
                .bookStatus(bookItem.getBookStatus())
                .purchaseDate(bookItem.getPurchaseDate())
                .publicationDate(bookItem.getPublicationDate())
                .rackId(bookItem.getRackId())
                .build();
    }

    public BookItemResponseDto toResponseDto(BookItem bookItem) {
        if (bookItem == null) return  null;

        return BookItemResponseDto.builder()
                .id(bookItem.getId())
                .book(bookMapper.toDto(bookItem.getBook()))
                .barcode(bookItem.getBarcode())
                .borrowed(bookItem.getBorrowed())
                .dueDate(bookItem.getDueDate())
                .price(bookItem.getPrice())
                .bookFormat(bookItem.getBookFormat())
                .bookStatus(bookItem.getBookStatus())
                .purchaseDate(bookItem.getPurchaseDate())
                .publicationDate(bookItem.getPublicationDate())
                .rackId(bookItem.getRackId())
                .build();
    }
}
