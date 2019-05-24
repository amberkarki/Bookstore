package com.BookStore.BookStore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface BookRepository extends CrudRepository <Book, Long>{
	List<Book> findByauthor(String author);

	
}
