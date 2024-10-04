package com.iapurba.bookapp.repository;

import com.iapurba.bookapp.model.entity.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookItemRepository extends JpaRepository<BookItem, Long> {
//    List<BookItem> findByISBN = (String bookISBN);
}
