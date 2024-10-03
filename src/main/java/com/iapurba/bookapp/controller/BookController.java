package com.iapurba.bookapp.controller;

import com.iapurba.bookapp.dto.BookDto;
import com.iapurba.bookapp.model.entity.Book;
import com.iapurba.bookapp.service.BookService;
import com.iapurba.bookapp.util.ResponseWrapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<Book>> createBook(
            @Valid @RequestBody BookDto bookDto) throws Exception {
            Book book = bookService.createBook(bookDto);
            return ResponseEntity.ok(new ResponseWrapper<>(
                    HttpStatus.OK.value(), "Book created successfully", book
            ));
    };

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<ResponseWrapper<Book>> getBookByIsbn(
            @PathVariable String isbn) throws Exception {
            Book book = bookService.getBookByIsbn(isbn);
            ResponseWrapper<Book> response = new ResponseWrapper<>(
                    HttpStatus.OK.value(), "Book retrieved successfully", book
            );
            return ResponseEntity.ok(response);
    };

    @PutMapping("/isbn/{isbn}")
    public ResponseEntity<ResponseWrapper<Book>> updateBook(
            @PathVariable String isbn,
            @Valid @RequestBody BookDto bookDto
    ) throws Exception {
        Book updatedBook = bookService.updateBook(isbn, bookDto);
        return ResponseEntity.ok(new ResponseWrapper<>(
                HttpStatus.OK.value(), "Book updated successfully", updatedBook));
    };

    @DeleteMapping("/isbn/{isbn}")
    public ResponseEntity<ResponseWrapper<?>> deleteBookByIsbn(
            @PathVariable String isbn) throws Exception {
        bookService.deleteBookByIsbn(isbn);
        return ResponseEntity.ok(new ResponseWrapper<>(
                HttpStatus.OK.value(), "Book deleted successfully", null));
    }
}
