package com.company.bookapi.book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    private static final List<Book> BOOKS = Arrays.asList(
      new Book(1, "Code Complete"),
      new Book(2, "Clean Code"),
      new Book(3, "Thinking in Java")
    );

    @GetMapping(path = "{bookId}")
    public Book getBook(@PathVariable("bookId") Integer bookId) {
        return BOOKS.stream()
                .filter(book -> bookId.equals(book.getBookId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Book " + bookId + " does not exists."
                ));
    }
}
