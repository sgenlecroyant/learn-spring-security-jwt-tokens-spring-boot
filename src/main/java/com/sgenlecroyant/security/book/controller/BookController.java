//package com.sgenlecroyant.security;
package com.sgenlecroyant.security.book.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	@Autowired
	private BookService bookService;

	@GetMapping(value = "/books/{id}")
//	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	private ResponseEntity<Book> fetchBookById(@PathVariable Long id){
		Book book = this.bookService.fetchBookById(id).get();
//		Book book2 = this.bookRepository.findById(id).get();
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	@PostMapping(value = "/books")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@Secured("hasRole('ROLE_ADMIN')")
	private ResponseEntity<Book> saveBook(@RequestBody Book book){
		return new ResponseEntity<Book>(this.bookService.saveBook(book), HttpStatus.OK);
	}
	
	@PatchMapping(value = "/books/{id}")
//	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
//	@PostAuthorize("isAuthenticated()")
	private ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book){
		return new ResponseEntity<Book>(this.bookService.updateBook(id, book), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/books/{id}")
//	@PreAuthorize(value = "hasAuthority('book:write')")
	private ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id){
		this.bookService.deleteBookById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping(value = "/books")
//	@PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ROLE_REG_USER')")
//	@Secured("ROLE_ADMIN")
	private ResponseEntity<List<Book>> fetchBooks(){
		return new ResponseEntity<List<Book>>(this.bookService.fetchBooks(), HttpStatus.OK);
	}

	
}
