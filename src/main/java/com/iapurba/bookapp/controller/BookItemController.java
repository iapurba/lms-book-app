package com.iapurba.bookapp.controller;

import com.iapurba.bookapp.dto.BookItemRequestDto;
import com.iapurba.bookapp.dto.BookItemResponseDto;
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
    public ResponseEntity<ResponseWrapper<BookItemResponseDto>> createUpdateBookItem(
            @Valid @RequestBody BookItemRequestDto requestDto) throws Exception {
        BookItemResponseDto responseDto = bookItemService.createUpdateBookItem(requestDto);
        String message = (requestDto.getId() == null)
                ? "BookItem created successfully"
                : "BookItem updated successfully";
        return ResponseEntity.ok(
                new ResponseWrapper<>(HttpStatus.OK.value(), message, responseDto)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<BookItemResponseDto>> getBookItemById(
            @PathVariable Long id) throws Exception {
        BookItemResponseDto responseDto = bookItemService.getBookItemById(id);
        return ResponseEntity.ok(new ResponseWrapper<>(
                HttpStatus.OK.value(), "BookItem retrieved successfully", responseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<?>> deleteBookItemById(
            @PathVariable Long id) throws Exception {
        bookItemService.deleteBookItemById(id);
        return ResponseEntity.ok(new ResponseWrapper<>(
                HttpStatus.OK.value(), "BookItem deleted successfully", null));
    }
}
