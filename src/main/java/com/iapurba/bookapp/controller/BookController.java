package com.iapurba.bookapp.controller;

import com.iapurba.bookapp.dto.BookDto;
import com.iapurba.bookapp.dto.BookSearchResultDto;
import com.iapurba.bookapp.service.BookService;
import com.iapurba.bookapp.util.ResponseWrapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<BookDto>> createBook(
            @Valid @RequestBody BookDto bookDto) throws Exception {
        BookDto book = bookService.createBook(bookDto);
            return ResponseEntity.ok(new ResponseWrapper<>(
                    HttpStatus.OK.value(), "Book created successfully", book
            ));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<ResponseWrapper<BookDto>> getBookByIsbn(
            @PathVariable String isbn) throws Exception {
            BookDto book = bookService.getBookByIsbn(isbn);
            ResponseWrapper<BookDto> response = new ResponseWrapper<>(
                    HttpStatus.OK.value(), "Book retrieved successfully", book
            );
            return ResponseEntity.ok(response);
    }

    @PutMapping("/isbn/{isbn}")
    public ResponseEntity<ResponseWrapper<BookDto>> updateBook(
            @PathVariable String isbn,
            @Valid @RequestBody BookDto bookDto
    ) throws Exception {
        BookDto updatedBook = bookService.updateBook(isbn, bookDto);
        return ResponseEntity.ok(new ResponseWrapper<>(
                HttpStatus.OK.value(), "Book updated successfully", updatedBook));
    }

    @DeleteMapping("/isbn/{isbn}")
    public ResponseEntity<ResponseWrapper<?>> deleteBookByIsbn(
            @PathVariable String isbn) throws Exception {
        bookService.deleteBookByIsbn(isbn);
        return ResponseEntity.ok(new ResponseWrapper<>(
                HttpStatus.OK.value(), "Book deleted successfully", null));
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseWrapper<List<BookSearchResultDto>>> searchBooks(
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) throws Exception {
        List<BookSearchResultDto> searchResults = bookService.searchBooks(isbn, title, author);
        return ResponseEntity.ok(new ResponseWrapper<>(
                HttpStatus.OK.value(), "Search result retrieved successfully", searchResults));
    }
}
