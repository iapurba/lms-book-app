package com.iapurba.bookapp.service;

import com.iapurba.bookapp.dto.AuthorDto;
import com.iapurba.bookapp.dto.BookDto;
import com.iapurba.bookapp.exception.AuthorNotFoundException;
import com.iapurba.bookapp.exception.BookNotFoundException;
import com.iapurba.bookapp.exception.DuplicateBookException;
import com.iapurba.bookapp.model.entity.Author;
import com.iapurba.bookapp.model.entity.Book;
import com.iapurba.bookapp.repository.AuthorRepository;
import com.iapurba.bookapp.repository.BookRepository;
import com.iapurba.bookapp.util.ErrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Transactional
    public Book createBook(BookDto bookDto) throws Exception {
        if (bookRepository.existsByIsbn(bookDto.getIsbn())) {
            throw new DuplicateBookException(ErrorConstants.DUPLICATE_BOOK_ERROR_MESSAGE);
        }
        return saveBook(bookDto, null);
    }

    @Transactional
    public Book updateBook(String isbn, BookDto bookDto) throws Exception {
        Book existingBook = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException("Book with ISBN " + isbn + " not found"));

        return saveBook(bookDto, existingBook);
    }

    @Transactional(readOnly = true)
    public Book getBookByIsbn(String isbn) throws Exception {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException("Book with ISBN " + isbn + " not found"));
    }

    @Transactional
    public void deleteBookByIsbn(String isbn) throws Exception {
        if (bookRepository.existsByIsbn(isbn)) {
            bookRepository.deleteByIsbn(isbn);
        } else {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found");
        }
    }

    private Book saveBook(BookDto bookDto, Book existingBook) throws Exception {
        List<Author> authors = new ArrayList<>();
        for (AuthorDto authorDto : bookDto.getAuthors()) {
            Author author;
            if (authorDto.getId() != null) {
                // If author id is provided check if it exists
                author = authorRepository.findById(authorDto.getId())
                        .orElseThrow(() -> new AuthorNotFoundException("Author with " + authorDto.getId() + " not found"));
            } else {
                author = new Author();
                author.setName(authorDto.getName());
                author.setBiography(authorDto.getBiography());
                author = authorRepository.save(author);
            }
            authors.add(author);
        }

        Book book = (existingBook != null) ? existingBook : new Book();

        book.setIsbn(bookDto.getIsbn());
        book.setTitle(bookDto.getTitle());
        book.setSubject(bookDto.getSubject());
        book.setDescription(bookDto.getDescription());
        book.setLanguage(bookDto.getLanguage());
        book.setPublisher(bookDto.getPublisher());
        book.setNumOfPages(bookDto.getNumOfPages());
        book.setAuthors(authors);

        return bookRepository.save(book);
    }
}
