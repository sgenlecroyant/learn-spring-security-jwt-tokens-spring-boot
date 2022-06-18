package com.sgenlecroyant.security.book.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgenlecroyant.security.book.action.OnBookAction;
import com.sgenlecroyant.security.book.entity.Book;
import com.sgenlecroyant.security.book.repository.BookRepository;

@Service
public class BookService implements OnBookAction{
	
	private final BookRepository bookRepository;

	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Optional<Book> fetchBookById(Long id) {
		
		return this.bookRepository.findById(id);
	}

	@Override
	public Book saveBook(Book book) {
		return this.bookRepository.save(book);
	}

	@Override
	public List<Book> fetchBooks() {
		return this.bookRepository.findAll();
	}

	@Override
	public Book updateBook(Long id, Book book) {
		return this.bookRepository
					.findById(id)
					.map((fetchedBook) -> {
						fetchedBook.setAuthor(book.getAuthor());
						fetchedBook.setTitle(book.getTitle());
						this.bookRepository.save(fetchedBook);
						return fetchedBook;
					}).orElseThrow(() -> {
						return new RuntimeException("No Book with ID: " +id);
					});
	}

	@Override
	public void deleteBookById(Long id) {
		this.bookRepository.deleteById(id);
	}

}
