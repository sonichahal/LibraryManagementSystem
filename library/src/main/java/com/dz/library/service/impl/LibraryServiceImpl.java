package com.dz.library.service.impl;

import com.dz.library.model.Book;
import com.dz.library.repository.LibraryDao;
import com.dz.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryDao libraryDao;

    @Override
    public Book addBook(Book book) {
        return libraryDao.addBook(book);
    }

    @Override
    public List<Book> getById(String bookId) {
        return libraryDao.getById(bookId);
    }

    @Override
    @Cacheable(value = "getBooks", key = "'books'")
    public List<Book> getBooks() {
        System.out.println("Retrieving data from the database");
        return libraryDao.getBooks();
    }

    @Override
    public Object getBookQuantity(String bookId) {
        return libraryDao.getBookQuantity(bookId);
    }

    @Override
    public Integer updateBook(Book book, String quantity) {
        return libraryDao.updateBook(book, quantity);
    }

    @Override
    public Integer updateBookQuantity(String bookId, Integer quantity) {
        return libraryDao.updateBookQuantity(bookId, quantity);
    }

    @Override
    @CacheEvict(value = "getbooks", key = "'deleteBook'")
    public Integer deleteBookById(String bookId) {
        return libraryDao.deleteBookById(bookId);
    }
}
