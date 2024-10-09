package com.iapurba.bookapp.repository;

import com.iapurba.bookapp.model.entity.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookItemRepository extends JpaRepository<BookItem, Long> {
    @Query("SELECT COUNT(bi) FROM BookItem bi WHERE bi.book.id = :bookId AND bi.bookStatus = 'AVAILABLE'")
    int countAvailableBookItems(@Param("bookId") Long bookId);
}
