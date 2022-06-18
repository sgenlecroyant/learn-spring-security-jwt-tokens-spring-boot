package com.sgenlecroyant.security.book.action;

import java.util.Optional;

import com.sgenlecroyant.security.book.entity.Book;

public interface OnBookAction {
	
	public Optional<Book> fetchBookById(Long id);
	public Book saveBook(Book book);
	public void fetchBooks();
	public Book updateBook(Long id, Book book);
	public void deleteBookById(Long id);

}
