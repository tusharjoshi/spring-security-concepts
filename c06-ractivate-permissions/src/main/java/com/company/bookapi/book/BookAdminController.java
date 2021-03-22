package com.company.bookapi.book;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("admin/api/v1/books")
public class BookAdminController {

    private static final List<Book> BOOKS = Arrays.asList(
            new Book(1, "Code Complete"),
            new Book(2, "Clean Code"),
            new Book(3, "Thinking in Java")
    );

    @GetMapping
    public List<Book> getAllBooks() {
        System.out.println("Book List requested.");
        return BOOKS;
    }

    @PostMapping
    public void addBook(Book book) {
        System.out.println("Added Book " + book.getBookName());
    }

    @DeleteMapping(path = "{bookId}")
    public void deleteBook(@PathVariable("bookId") Integer bookId) {
        System.out.println("Deleted Book " + bookId);
    }

    @PutMapping(path = "{bookId}")
    public void updateBook(@PathVariable("bookId") Integer bookId, @RequestBody Book book) {
        System.out.println(
                String.format("Updated Book id: %s name %s",bookId, book.getBookName())
        );
    }
}
