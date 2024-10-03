package com.iapurba.bookapp.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.iapurba.bookapp.dto.AuthorDto;
import com.iapurba.bookapp.dto.BookDto;
import com.iapurba.bookapp.model.entity.Book;
import com.iapurba.bookapp.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private Book book;
    private BookDto bookDto;
    private ObjectMapper objectMapper;

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

        objectMapper = new ObjectMapper();
    }

    @Test
    public void createBookTest() throws Exception {
        when(bookService.createBook(any(BookDto.class))).thenReturn(book);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Book created successfully"))
                .andExpect(jsonPath("$.data.isbn").value("978-0596009205"))
                .andExpect(jsonPath("$.data.title").value("Head First Java"));

        verify(bookService, times(1)).createBook(any(BookDto.class));
    }

    @Test
    public void getBookByIsbnTest() throws Exception {
        when(bookService.getBookByIsbn(anyString())).thenReturn(book);

        mockMvc.perform(get("/api/books/isbn/978-0596009205")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Book retrieved successfully"))
                .andExpect(jsonPath("$.data.isbn").value("978-0596009205"));

        verify(bookService, times(1)).getBookByIsbn(anyString());
    }

}
