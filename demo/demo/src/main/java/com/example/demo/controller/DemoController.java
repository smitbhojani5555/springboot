package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

@RestController
public class DemoController {

	@Autowired
	BookService booksService;

	@PostMapping("/book")
	private String book() {
		return "success";
	}

	@PostMapping("/addBooks")
	private List<Book> addBooks(@RequestBody List<Book> books) {
		return booksService.saveBooks(books);
	}

	@PostMapping("/addBook")
	private Book addBook(@RequestBody Book book) {
		return booksService.saveBook(book);
	}

	@GetMapping("/getBookById/{id}")
	private Book getBookById(@PathVariable("id") int id) {
		return booksService.getBookById(id);
	}

	@GetMapping("/getAllBook")
	private List<Book> getAllBook() {
		return booksService.getAllBooks();
	}

	@DeleteMapping("/deleteBook/{id}")
	private String deleteBook(@PathVariable("id") int id) {
		return booksService.deleteBook(id);
	}

	@PutMapping("/updateBook")
	private int updateBook(@RequestBody Book book) {
		booksService.updateBook(book);
		return book.getId();
	}

}
