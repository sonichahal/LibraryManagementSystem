package com.dz.library.controller;

import com.dz.library.model.Book;
import com.dz.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        return libraryService.addBook(book);
    }

    @GetMapping("/getBookById")
    public List<Book> getById(@RequestParam String bookId) {
        return libraryService.getById(bookId);
    }

    @GetMapping("/bookQuantity")
    public Object getBookQuantity(@RequestParam String bookId) {
        return libraryService.getBookQuantity(bookId);
    }

    @PutMapping("/updateBook")
    public ResponseEntity<Book> updateBookQuantity(@RequestBody Book book, String quantity) {
        if (Objects.nonNull(libraryService.updateBook(book, quantity))) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/updateById/{bookId}/{quantity}")
    public ResponseEntity<Book> updateBookByQuantity(@PathVariable("bookId") String bookId, @PathVariable("quantity") Integer quantity) {
        if (Objects.nonNull(libraryService.updateBookQuantity(bookId, quantity))) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
