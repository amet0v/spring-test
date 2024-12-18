package com.ametov.spring.book.controller;

import com.ametov.spring.base.exception.ResourceNotFoundException;
import com.ametov.spring.book.entity.BookEntity;
import com.ametov.spring.book.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public BookEntity byId(@PathVariable Integer id){
        return BookService.byId(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping("/api/v1/book")
    public BookEntity create(@RequestBody BookEntity request){
        return bookService.create(request.getTitle(), request.getDescription());
    }

    @PutMapping("/api/v1/book")
    BookEntity edit(@RequestBody BookEntity request){
        return bookService.edit(request).orElseThrow(ResourceNotFoundException::new);
    }

    @DeleteMapping("/api/v1/book/{id}")
    public Boolean delete(@PathVariable Integer id){
        return bookService.delete(id);
    }
}
