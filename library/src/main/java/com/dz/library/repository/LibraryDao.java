package com.dz.library.repository;

import com.dz.library.model.Book;

import java.util.List;

public interface LibraryDao {

    Book addBook(Book book);

    Object getBookQuantity(String bookId);

    List<Book> getById(String bookId);

    Integer updateBook(Book bookId, String quantity);

    Integer updateBookQuantity(String bookId, Integer quantity);
}
