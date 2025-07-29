package com.bookstore.controller;

import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {
    @Autowired
    private BookRepository bookRepo;

    @GetMapping("/featured")
    public List<Book> getFeaturedBooks() {
        return bookRepo.findByPurchasedFalse();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return bookRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/purchase/{id}")
    public ResponseEntity<?> markAsPurchased(@PathVariable Long id) {
        return bookRepo.findById(id).map(book -> {
            book.setPurchased(true);
            bookRepo.save(book);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
    
    
    
}