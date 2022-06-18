package com.sgenlecroyant.security.book.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgenlecroyant.security.book.entity.Book;
import com.sgenlecroyant.security.book.service.BookService;

@RestController
public class BookController {
	
	private BookService bookService;
	
	@GetMapping(value = "/books/{id}")
	private ResponseEntity<Book> fetchBookById(@PathVariable Long id){
		return new ResponseEntity<Book>(this.bookService.fetchBookById(id).get(), HttpStatus.OK);
	}

	@PostMapping(value = "/books")
	private ResponseEntity<Book> saveBook(@RequestBody Book book){
		return new ResponseEntity<Book>(this.bookService.saveBook(book), HttpStatus.OK);
	}
	
	@PatchMapping(value = "/books/{id}")
	private ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book){
		return new ResponseEntity<Book>(this.bookService.updateBook(id, book), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/books/{id}")
	private ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id){
		this.bookService.deleteBookById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping(value = "/books")
	private ResponseEntity<List<Book>> fetchBooks(){
		return new ResponseEntity<List<Book>>(this.bookService.fetchBooks(), HttpStatus.OK);
	}
	
}
