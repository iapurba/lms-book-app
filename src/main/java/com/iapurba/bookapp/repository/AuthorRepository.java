package com.iapurba.bookapp.repository;

import com.iapurba.bookapp.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
