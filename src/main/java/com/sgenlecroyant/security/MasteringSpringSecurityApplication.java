package com.sgenlecroyant.security;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgenlecroyant.security.book.entity.Book;
import com.sgenlecroyant.security.book.repository.BookRepository;
import com.sgenlecroyant.security.book.service.BookService;

@SpringBootApplication
//@RestController
public class MasteringSpringSecurityApplication implements CommandLineRunner{
	
	@Autowired
	private BookRepository bookRepository;
//	
//	@Autowired
//	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(MasteringSpringSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Book loveYouToTheMoon = new Book("Love You To The Moon", new Date(), "Sheeran");
		Book riskyBet = new Book("The Risky Bet", new Date(), "Jeena");
		
		this.bookRepository.saveAll(List.of(loveYouToTheMoon, riskyBet));

	}
	
//	@GetMapping(value = "/")
//	private String getWelcomeMessage() {
//		return "Welcome to Spring Security!";
//	}
	

}
