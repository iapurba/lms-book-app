package com.iapurba.bookapp.service;

import com.iapurba.bookapp.dto.BookItemDto;
import com.iapurba.bookapp.model.entity.BookItem;

public interface BookItemService {
    public BookItem createUpdateBookItem(BookItemDto bookItemDto) throws Exception;
    public BookItem getBookItemById(Long id) throws Exception;
    public void deleteBookItemById(Long id) throws Exception;
}
