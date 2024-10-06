package com.iapurba.bookapp.service;

import com.iapurba.bookapp.dto.BookDto;
import com.iapurba.bookapp.model.entity.Book;

import java.util.Optional;

public interface BookService {
    public BookDto createBook(BookDto bookDto) throws Exception;
    public BookDto updateBook(String isbn, BookDto bookDto) throws Exception;
    public BookDto getBookByIsbn(String isbn) throws Exception;
    public void deleteBookByIsbn(String isbn) throws Exception;
}
