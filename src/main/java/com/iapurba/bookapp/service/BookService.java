package com.iapurba.bookapp.service;

import com.iapurba.bookapp.dto.BookDto;

public interface BookService {
    BookDto createBook(BookDto bookDto) throws Exception;
    BookDto updateBook(String isbn, BookDto bookDto) throws Exception;
    BookDto getBookByIsbn(String isbn) throws Exception;
    void deleteBookByIsbn(String isbn) throws Exception;
}
