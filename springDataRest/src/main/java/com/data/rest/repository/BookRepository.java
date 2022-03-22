package com.data.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.data.rest.entity.BookCollection;
@RepositoryRestResource(path = "MyBooks",collectionResourceRel = "book")
public interface BookRepository extends JpaRepository<BookCollection, Long>{

}
