package com.dz.library.service;

import com.dz.library.model.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LibraryService {

    Book addBook(Book book);

    List<Book> getBooks();

    Object getBookQuantity(String bookId);

    List<Book> getById(String bookId);

    Integer updateBook(Book book, String quantity);

    Integer updateBookQuantity(String bookId, Integer quantity);

    Integer deleteBookById(String bookId);
}
