package com.bfi.springBootMongoDb.controller;

import com.bfi.springBootMongoDb.entity.Book;
import com.bfi.springBootMongoDb.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/addBook")
    public ResponseEntity<HttpStatus> saveBook(@RequestBody Book book){
        bookRepository.save(book);
        LOG.info("Added book with id : " + book.getId());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/findAllBooks")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> allBooksList = bookRepository.findAll() ;
        return new ResponseEntity(allBooksList , HttpStatus.FOUND);
    }

    @GetMapping("/findAllBooks/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable String id){
        Optional<Book> book = bookRepository.findById(id);
        return new ResponseEntity(book , HttpStatus.FOUND );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable String id ){
        bookRepository.deleteById(id);
        LOG.info("book deleted with id " + id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
