package com.iapurba.bookapp.service;

import com.iapurba.bookapp.dto.BookItemDto;
import com.iapurba.bookapp.exception.BookItemNotFoundException;
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

    @Transactional
    public BookItem createUpdateBookItem(BookItemDto bookItemDto) throws Exception {
        BookItem bookItem;
        if (bookItemDto.getId() != null) {
            bookItem = bookItemRepository.findById(bookItemDto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("BookItem not found with id: " + bookItemDto.getId()));
        } else {
            bookItem = new BookItem();
        }

        Book book = bookRepository.findById(bookItemDto.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + bookItemDto.getBookId()));

        bookItem.setBook(book);
        bookItem.setBarcode(bookItemDto.getBarcode());
        bookItem.setReferenceOnly(bookItemDto.isReferenceOnly());
        bookItem.setBorrowed(bookItemDto.getBorrowed());
        bookItem.setDueDate(bookItemDto.getDueDate());
        bookItem.setPrice(bookItemDto.getPrice());
        bookItem.setBookFormat(bookItemDto.getBookFormat());
        bookItem.setBookStatus(bookItemDto.getBookStatus());
        bookItem.setPurchaseDate(bookItemDto.getPurchaseDate());
        bookItem.setPublicationDate(bookItemDto.getPublicationDate());
        bookItem.setRackId(bookItemDto.getRackId());

        return bookItemRepository.save(bookItem);
    };

    @Transactional
    public BookItem getBookItemById(Long id) throws Exception {
        return bookItemRepository.findById(id)
                .orElseThrow(() -> new BookItemNotFoundException("BookItem with id: " + id + " not found"));
    };

    @Transactional
    public void deleteBookItemById(Long id) throws Exception {
        if (bookItemRepository.existsById(id)) {
            bookItemRepository.deleteById(id);
        } else {
            throw new BookItemNotFoundException("BookItem with id: " + id + " not found");
        }
    };
}
