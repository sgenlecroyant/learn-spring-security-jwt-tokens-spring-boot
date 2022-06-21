package com.sgenlecroyant.security;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgenlecroyant.security.book.entity.Book;
import com.sgenlecroyant.security.book.repository.BookRepository;

@SpringBootApplication
public class MasteringSpringSecurityApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(MasteringSpringSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Book loveYouToTheMoon = new Book("Love You To The Moon", new Date(), "Sheeran");
		Book riskyBet = new Book("The Risky Bet", new Date(), "Jeena");

		this.bookRepository.saveAll(List.of(loveYouToTheMoon, riskyBet));

	}

}
