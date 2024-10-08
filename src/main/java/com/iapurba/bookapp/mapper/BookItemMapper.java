package com.iapurba.bookapp.mapper;

import com.iapurba.bookapp.dto.BookItemRequestDto;
import com.iapurba.bookapp.dto.BookItemResponseDto;
import com.iapurba.bookapp.model.entity.BookItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookItemMapper implements RequestMapper<BookItem, BookItemRequestDto>,
        ResponseMapper<BookItem, BookItemResponseDto> {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public BookItemRequestDto toRequestDto(BookItem bookItem) {
        if (bookItem == null) return null;

        return BookItemRequestDto.builder()
                .id(bookItem.getId())
                .bookIsbn(bookItem.getBook().getIsbn())
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

    @Override
    public BookItemResponseDto toResponseDto(BookItem bookItem) {
        if (bookItem == null) return null;

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
