package com.iapurba.bookapp.service;

import com.iapurba.bookapp.dto.BookItemRequestDto;
import com.iapurba.bookapp.dto.BookItemResponseDto;
import com.iapurba.bookapp.exception.BookItemNotFoundException;
import com.iapurba.bookapp.exception.BookNotFoundException;
import com.iapurba.bookapp.mapper.BookItemMapper;
import com.iapurba.bookapp.model.entity.Book;
import com.iapurba.bookapp.model.entity.BookItem;
import com.iapurba.bookapp.repository.BookItemRepository;
import com.iapurba.bookapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookItemServiceImpl implements BookItemService {

    @Autowired
    BookItemRepository bookItemRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookItemMapper bookItemMapper;

    @Transactional
    public BookItemResponseDto createUpdateBookItem(BookItemRequestDto bookItemDto) throws Exception {
        BookItem bookItem;
        if (bookItemDto.getId() != null) {
            bookItem = bookItemRepository.findById(bookItemDto.getId())
                    .orElseThrow(() -> new BookItemNotFoundException("BookItem not found with id: " + bookItemDto.getId()));
        } else {
            bookItem = new BookItem();
        }

        Book book = bookRepository.findByIsbn(bookItemDto.getBookIsbn())
                .orElseThrow(() -> new BookNotFoundException("Book not found with ISBN: " + bookItemDto.getBookIsbn()));

        bookItem.setBook(book);
        bookItem.setBarcode(bookItemDto.getBarcode());
        bookItem.setBorrowed(bookItemDto.getBorrowed());
        bookItem.setDueDate(bookItemDto.getDueDate());
        bookItem.setPrice(bookItemDto.getPrice());
        bookItem.setBookFormat(bookItemDto.getBookFormat());
        bookItem.setBookStatus(bookItemDto.getBookStatus());
        bookItem.setPurchaseDate(bookItemDto.getPurchaseDate());
        bookItem.setPublicationDate(bookItemDto.getPublicationDate());
        bookItem.setRackId(bookItemDto.getRackId());

        bookItem = bookItemRepository.save(bookItem);

        return bookItemMapper.toResponseDto(bookItem);
    }

    @Transactional
    public BookItemResponseDto getBookItemById(Long id) throws Exception {
        BookItem bookItem = bookItemRepository.findById(id)
                .orElseThrow(() -> new BookItemNotFoundException("BookItem with id: " + id + " not found"));

        return bookItemMapper.toResponseDto(bookItem);
    }

    @Transactional
    public void deleteBookItemById(Long id) throws Exception {
        if (bookItemRepository.existsById(id)) {
            bookItemRepository.deleteById(id);
        } else {
            throw new BookItemNotFoundException("BookItem with id: " + id + " not found");
        }
    }

}
