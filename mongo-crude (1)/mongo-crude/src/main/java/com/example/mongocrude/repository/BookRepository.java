package com.example.mongocrude.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mongocrude.entity.Book;

public interface BookRepository extends MongoRepository<Book, Long>{

}
