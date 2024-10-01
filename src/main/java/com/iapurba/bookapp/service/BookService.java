package com.iapurba.bookapp.service;

import com.iapurba.bookapp.dto.BookDto;
import com.iapurba.bookapp.model.entity.Book;

import java.util.Optional;

public interface BookService {
    public Book createBook(BookDto bookDto) throws Exception;
    public Book updateBook(String isbn, BookDto bookDto) throws Exception;
    public Book getBookByIsbn(String isbn) throws Exception;
    public void deleteBookByIsbn(String isbn) throws Exception;
}
