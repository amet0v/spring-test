package com.ametov.spring.book.service;

import com.ametov.spring.book.entity.BookEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BookService {
    static List<BookEntity> bookStorage = new ArrayList<>();

    public BookService() {
        fillStorage();
    }

    public void fillStorage() {
        String desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce dapibus magna ac mauris malesuada, posuere efficitur nulla volutpat. Mauris laoreet a dolor sit amet iaculis. Ut consequat tellus ac orci sodales aliquet. In hac habitasse platea dictumst. Curabitur leo nisl, vestibulum quis sagittis eu, rutrum quis orci. Ut sit amet quam venenatis, efficitur dolor sit amet, tincidunt enim. Nunc ut odio arcu. Fusce ultrices velit eu eros vestibulum varius.";

        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            bookStorage.add(new BookEntity(i+1,
                    "Book" + random.nextInt(100, 999),
                    desc.substring(0, random.nextInt(0, desc.length()))));
        }
    }

    public static List<BookEntity> all() {
        return bookStorage;
    }

    public static Optional<BookEntity> byId(Integer id){
        return bookStorage.stream().filter((bookEntity -> bookEntity.getId().equals(id))).findFirst();
    }

    public BookEntity create(String title, String description){
        BookEntity book = new BookEntity();
        book.setId(bookStorage.size() + 1);
        book.setTitle(title);
        book.setDescription(description);
        bookStorage.add(book);
        return book;
    }

    public Optional<BookEntity> edit(BookEntity book){
        Optional<BookEntity> oldBook = byId(book.getId());
        if (oldBook.isEmpty()){
            return Optional.empty();
        }
        BookEntity oldBookEntity = oldBook.get();
        oldBookEntity.setTitle(book.getTitle());
        oldBookEntity.setDescription(book.getDescription());
        return Optional.of(oldBookEntity);
    }

    public Boolean delete(Integer id){
        Optional<BookEntity> book = byId(id);
        if (book.isEmpty()){
            return false;
        }
        bookStorage.remove(book.get());
        return true;
    }
}
