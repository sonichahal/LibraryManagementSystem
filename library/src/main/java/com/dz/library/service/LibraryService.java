package com.dz.library.service;

import com.dz.library.model.Book;

import java.util.List;

public interface LibraryService {

    Book addBook(Book book);

    Object getBookQuantity(String bookId);

    List<Book> getById(String bookId);

    Integer updateBook(Book book, String quantity);

    Integer updateBookQuantity(String bookId, Integer quantity);
}
