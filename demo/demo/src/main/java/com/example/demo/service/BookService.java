package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	public List<Book> saveBooks(List<Book> books) {
		return bookRepository.saveAll(books);
	}

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public Book getBookById(int id) {
		return bookRepository.findById(id).orElse(null);
	}

	public String deleteBook(int id) {
		bookRepository.deleteById(id);
		return "success";
	}
	
	public Book updateBook(Book book) {
		Book existBook = bookRepository.findById(book.getId()).orElse(null);
		existBook.setName(book.getName());
		existBook.setPrice(book.getPrice());
		existBook.setAuthor(book.getAuthor());
		return bookRepository.save(existBook);
	}

}
