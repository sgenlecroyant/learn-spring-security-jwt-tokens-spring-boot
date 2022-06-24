package com.sgenlecroyant.security.book.repository;

//package com.sgenlecroyant.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgenlecroyant.security.book.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
