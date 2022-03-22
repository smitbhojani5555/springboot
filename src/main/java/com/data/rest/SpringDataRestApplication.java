package com.data.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.data.rest.entity.BookCollection;
import com.data.rest.repository.BookRepository;

@SpringBootApplication
public class SpringDataRestApplication implements CommandLineRunner {
	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataRestApplication.class, args);
	}

	

	@Override
	public void run(String... args) throws Exception {
		BookCollection book = new BookCollection();
		book.setTitle("My First Book");
		book.setAuthor("Smit Patel");
		book.setPages(100);
		this.bookRepository.save(book);
		
	}

}
