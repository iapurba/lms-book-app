package com.iapurba.bookapp.mapper;

import com.iapurba.bookapp.dto.BookDto;
import com.iapurba.bookapp.model.entity.Book;

public class BookMapper {
    private final AuthorMapper authorMapper = new AuthorMapper();

    public BookDto toDto(Book book) {
        if (book == null) return null;

        return BookDto.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .subject(book.getSubject())
                .authors(authorMapper.convertToDtoList(book.getAuthors()))
                .description(book.getDescription())
                .numOfPages(book.getNumOfPages())
                .language(book.getLanguage())
                .publisher(book.getPublisher())
                .build();
    }

    public Book toEntity(BookDto bookDto) {
        if (bookDto == null) return null;

        Book book = new Book();
        book.setId(bookDto.getId());
        book.setIsbn(bookDto.getIsbn());
        book.setTitle(bookDto.getTitle());
        book.setSubject(bookDto.getSubject());
        book.setAuthors(authorMapper.convertToEntityList(bookDto.getAuthors()));
        book.setDescription(bookDto.getDescription());
        book.setNumOfPages(bookDto.getNumOfPages());
        book.setLanguage(bookDto.getLanguage());
        book.setPublisher(bookDto.getPublisher());

        return book;
    }
}
