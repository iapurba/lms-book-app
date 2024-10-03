package com.iapurba.bookapp.controller;

import com.iapurba.bookapp.dto.AuthorDto;
import com.iapurba.bookapp.dto.BookDto;
import com.iapurba.bookapp.model.entity.Book;
import com.iapurba.bookapp.service.BookService;
import com.iapurba.bookapp.util.ResponseWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    private Book book;
    private BookDto bookDto;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setIsbn("978-0596009205");
        book.setTitle("Head First Java");

        List<AuthorDto> authors = new ArrayList<>();
        authors.add(new AuthorDto(1L, "Kathy Sierra", null));
        authors.add(new AuthorDto(2L, "Bert Bates", null));

        bookDto = BookDto.builder()
                .isbn("978-0596009205")
                .title("Head First Java")
                .subject("Technology")
                .language("English")
                .publisher("O'Reilly Media")
                .numOfPages(720)
                .authors(authors)
                .build();
    }

    @Test
    public void createBookTest() throws Exception {
        when(bookService.createBook(any(BookDto.class))).thenReturn(book);
        ResponseEntity<ResponseWrapper<Book>> response = bookController.createBook(bookDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book created successfully", Objects.requireNonNull(response.getBody()).getMessage());
        assertEquals(book, response.getBody().getData());

        verify(bookService, times(1)).createBook(any(BookDto.class));
    }

    @Test
    public void getBookByIsbnTest() throws Exception {
        when(bookService.getBookByIsbn("978-0596009205")).thenReturn(book);
        ResponseEntity<ResponseWrapper<Book>> response = bookController.getBookByIsbn("978-0596009205");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book retrieved successfully", Objects.requireNonNull(response.getBody()).getMessage());
        assertEquals("978-0596009205", response.getBody().getData().getIsbn());
        assertEquals(book, response.getBody().getData());

        verify(bookService, times(1)).getBookByIsbn("978-0596009205");
    }

    @Test
    public void updateBookTest() throws Exception {
        when(bookService.updateBook(anyString(), any(BookDto.class))).thenReturn(book);
        ResponseEntity<ResponseWrapper<Book>> response = bookController.updateBook("978-0596009205", bookDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book updated successfully", Objects.requireNonNull(response.getBody()).getMessage());
        assertEquals(book, response.getBody().getData());

        verify(bookService, times(1)).updateBook(anyString(), any(BookDto.class));
    }

    @Test
    public void deleteBookByIsbnTest() throws Exception {
        doNothing().when(bookService).deleteBookByIsbn("978-0596009205");
        ResponseEntity<ResponseWrapper<?>> response = bookController.deleteBookByIsbn("978-0596009205");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book deleted successfully", Objects.requireNonNull(response.getBody()).getMessage());
        assertNull(response.getBody().getData());

        verify(bookService, times(1)).deleteBookByIsbn("978-0596009205");
    }

}
