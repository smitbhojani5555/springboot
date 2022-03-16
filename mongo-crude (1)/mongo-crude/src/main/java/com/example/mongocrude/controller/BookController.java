package com.example.mongocrude.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongocrude.entity.Book;
import com.example.mongocrude.repository.BookRepository;

@RestController
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@PostMapping("/addBook")
	public String saveBook(@RequestBody Book book) {
		bookRepository.save(book);
		return "success";
	}

	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@GetMapping("/getBookById/{id}")
	public Optional<Book> getBookById(@PathVariable Long id) {
		return bookRepository.findById(id);
	}

	@DeleteMapping("/deleteBookById/{id}")
	public String deleteBookById(@PathVariable Long id) {
		bookRepository.deleteById(id);
		return "Book deleted by ID" + id;
	}

}
