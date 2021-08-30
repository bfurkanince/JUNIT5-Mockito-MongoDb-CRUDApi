package com.bfi.springBootMongoDb.repository;

import com.bfi.springBootMongoDb.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

}
