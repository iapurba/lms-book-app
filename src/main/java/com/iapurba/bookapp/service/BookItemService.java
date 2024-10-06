package com.iapurba.bookapp.service;

import com.iapurba.bookapp.dto.BookItemRequestDto;
import com.iapurba.bookapp.dto.BookItemResponseDto;
import com.iapurba.bookapp.model.entity.BookItem;

public interface BookItemService {
    public BookItemResponseDto createUpdateBookItem(BookItemRequestDto bookItemDto) throws Exception;
    public BookItemResponseDto getBookItemById(Long id) throws Exception;
    public void deleteBookItemById(Long id) throws Exception;
}
