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
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookDto bookDto) throws Exception {
            Book book = bookService.createBook(bookDto);
            return ResponseEntity.ok(book);
    };

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Book>> getBookById(@PathVariable Long id) {
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(
                    new ResponseWrapper<>(
                            HttpStatus.OK.value(),
                            "Book retrieved successfully",
                            book
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(
                            HttpStatus.NOT_FOUND.value(),
                            e.getMessage(),
                            null
                    ));
        }
    }

    ;

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {

    }
}
