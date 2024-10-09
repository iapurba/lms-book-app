package com.iapurba.bookapp.service;

import com.iapurba.bookapp.dto.BookItemRequestDto;
import com.iapurba.bookapp.dto.BookItemResponseDto;

public interface BookItemService {
    BookItemResponseDto createUpdateBookItem(BookItemRequestDto bookItemDto) throws Exception;

    BookItemResponseDto getBookItemById(Long id) throws Exception;

    void deleteBookItemById(Long id) throws Exception;
}
