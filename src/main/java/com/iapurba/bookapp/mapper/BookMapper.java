package com.iapurba.bookapp.mapper;

import com.iapurba.bookapp.dto.BookDto;
import com.iapurba.bookapp.model.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<Book, BookDto> {
    @Autowired
    private AuthorMapper authorMapper;

    @Override
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

    @Override
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
