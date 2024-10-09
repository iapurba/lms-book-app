package com.iapurba.bookapp.service;

import com.iapurba.bookapp.dto.BookDto;
import com.iapurba.bookapp.dto.BookSearchResultDto;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto bookDto) throws Exception;

    BookDto updateBook(String isbn, BookDto bookDto) throws Exception;

    BookDto getBookByIsbn(String isbn) throws Exception;

    void deleteBookByIsbn(String isbn) throws Exception;

    List<BookSearchResultDto> searchBooks(String isbn, String title, String author) throws  Exception;
}
