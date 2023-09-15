package com.lucas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucas.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{}
