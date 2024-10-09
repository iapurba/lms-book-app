package com.iapurba.bookapp.repository;

import com.iapurba.bookapp.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    void deleteByIsbn(String isbn);

    @Query(value = "SELECT DISTINCT b.* FROM books b " +
            "JOIN books_authors ba ON b.isbn = ba.book_isbn " +
            "JOIN authors a ON a.id = ba.author_id " +
            "WHERE (:isbn IS NULL OR b.isbn = :isbn) " +
            "AND (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
            "AND (:author IS NULL OR LOWER(a.name) LIKE LOWER(CONCAT('%', :author, '%')))",
            nativeQuery = true)
    List<Book> searchBooks(@Param("isbn") String isbn,
                           @Param("title") String title,
                           @Param("author") String author);
}
