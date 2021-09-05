package com.bfi.springBootMongoDb;

import com.bfi.springBootMongoDb.controller.BookController;
import com.bfi.springBootMongoDb.entity.Book;
import com.bfi.springBootMongoDb.repository.BookRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static java.util.Optional.ofNullable;

@SpringBootTest(classes = {BookControllerMockitoTest.class})
public class BookControllerMockitoTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookRepository bookRepository;

    private Book book ;
    private List<Book> booksList;

    @Test
    @Order(1)
    public void test_saveBook(){
        book = new Book("12","test_SaveBook","test_SaveBook");
        when(bookRepository.save(book)).thenReturn(book);//Mocking
        ResponseEntity<HttpStatus> res = bookController.saveBook(book);
        assertEquals(HttpStatus.CREATED , res.getStatusCode());
    }

    @Test
    @Order(2)
    public void test_getBooks(){
        booksList = new ArrayList<>();
        booksList.add(new Book("12","test_SaveBook","test_SaveBook"));
        booksList.add(new Book("13","test_SaveBook","test_SaveBook"));
        booksList.add(new Book("14","test_SaveBook","test_SaveBook"));
        when(bookRepository.findAll()).thenReturn(booksList);//Mocking
        ResponseEntity<List<Book>> res = bookController.getBooks();
        assertEquals(HttpStatus.FOUND , res.getStatusCode());
        assertEquals(booksList.size() , booksList.size());

    }

    @Test
    @Order(3)
    public void test_getBookById(){
        String id = "12";
        book = new Book("12","test_SaveBook","test_SaveBook");
        when(bookRepository.findById(id)).thenReturn(ofNullable(book));//Mocking
        ResponseEntity<Optional<Book>> res = bookController.getBookById(book.getId());
        assertEquals(HttpStatus.FOUND , res.getStatusCode());
        assertEquals(res.getBody().isPresent() , Boolean.TRUE);
    }

}
