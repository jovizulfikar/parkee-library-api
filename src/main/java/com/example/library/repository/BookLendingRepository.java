package com.example.library.repository;

import com.example.library.model.entity.BookLending;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLendingRepository extends JpaRepository<BookLending, String> {
}
