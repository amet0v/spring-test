package com.ametov.spring.book.controller;

import com.ametov.spring.book.entity.BookEntity;
import com.ametov.spring.book.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BookApiController {
    private final BookService bookService;

    public BookApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String ok(){
        return "ok";
    }

    @GetMapping("/api/v1/book")
    public List<BookEntity> all(){
        return BookService.all();
    }

    @GetMapping("/api/v1/book/{id}")
    public Optional<BookEntity> byId(@PathVariable Integer id){
        return BookService.byId(id);
    }
}
