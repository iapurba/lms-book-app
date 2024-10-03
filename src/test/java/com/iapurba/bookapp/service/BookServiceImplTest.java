package com.iapurba.bookapp.service;

import com.iapurba.bookapp.dto.AuthorDto;
import com.iapurba.bookapp.dto.BookDto;
import com.iapurba.bookapp.model.entity.Author;
import com.iapurba.bookapp.model.entity.Book;
import com.iapurba.bookapp.repository.AuthorRepository;
import com.iapurba.bookapp.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    AuthorRepository authorRepository;

    private BookDto bookDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        AuthorDto authorDto = AuthorDto.builder()
                .id(null)
                .name("Test Author")
                .build();

        bookDto = BookDto.builder()
                .isbn("1234567890")
                .title("Test Book")
                .authors(Collections.singletonList(authorDto))
                .build();
    }

    @Test
    public void createBookTest_Success() throws Exception {
        // Mocks
        when(bookRepository.existsByIsbn(bookDto.getIsbn())).thenReturn(false);
        when(authorRepository.save(any(Author.class))).thenReturn(new Author());
        when(bookRepository.save(any(Book.class))).thenReturn(new Book());
        // Test
        Book createdBook = bookService.createBook(bookDto);

        //Verifications
        assertNotNull(createdBook);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void createBookTest_DuplicateError() throws Exception {
        // Mocks
        when(bookRepository.existsByIsbn(bookDto.getIsbn())).thenReturn(true);

    }
}
