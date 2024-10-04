package com.iapurba.bookapp.controller;

import com.iapurba.bookapp.dto.BookItemDto;
import com.iapurba.bookapp.model.entity.BookItem;
import com.iapurba.bookapp.service.BookItemService;
import com.iapurba.bookapp.util.ResponseWrapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book/items")
public class BookItemController {

    @Autowired
    BookItemService bookItemService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<BookItem>> createUpdateBookItem(
            @Valid @RequestBody BookItemDto bookItemDto) throws Exception {
        BookItem bookItem = bookItemService.createUpdateBookItem(bookItemDto);
        String message = (bookItemDto.getId() == null)
                ? "BookItem created successfully"
                : "BookItem updated successfully";
        return ResponseEntity.ok(
                new ResponseWrapper<>(HttpStatus.OK.value(), message, bookItem)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<BookItem>> getBookItemById(
            @PathVariable Long id) throws Exception {
        BookItem bookItem = bookItemService.getBookItemById(id);
        return ResponseEntity.ok(new ResponseWrapper<>(
                HttpStatus.OK.value(), "BookItem retrieved successfully", bookItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<?>> deleteBookItemById(
            @PathVariable Long id) throws Exception {
        bookItemService.deleteBookItemById(id);
        return ResponseEntity.ok(new ResponseWrapper<>(
                HttpStatus.OK.value(), "BookItem deleted successfully", null));
    }
}
