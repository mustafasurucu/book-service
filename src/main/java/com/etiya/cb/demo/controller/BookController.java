package com.etiya.cb.demo.controller;

import com.etiya.cb.demo.model.response.BookResponse;
import com.etiya.cb.demo.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("book")
    public ResponseEntity<BookResponse> getBook() {
        return ResponseEntity.ok(bookService.getBook());
    }
}
