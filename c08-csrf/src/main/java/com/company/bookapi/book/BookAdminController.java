package com.company.bookapi.book;

import org.springframework.security.access.prepost.PreAuthorize;
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

    // hasRole('ROLE_')
    // hasAnyRole('ROLE_')
    // hasAuthority('permission')
    // hasAnyAuthority('permission')

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_SUB_CON')")
    public List<Book> getAllBooks() {
        System.out.println("Book List requested.");
        return BOOKS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('book:write')")
    public void addBook(@RequestBody Book book) {
        System.out.println("Added Book " + book.getBookName());
    }

    @DeleteMapping(path = "{bookId}")
    @PreAuthorize("hasAuthority('book:write')")
    public void deleteBook(@PathVariable("bookId") Integer bookId) {
        System.out.println("Deleted Book " + bookId);
    }

    @PutMapping(path = "{bookId}")
    @PreAuthorize("hasAuthority('book:write')")
    public void updateBook(@PathVariable("bookId") Integer bookId, @RequestBody Book book) {
        System.out.println(
                String.format("Updated Book id: %s name %s",bookId, book.getBookName())
        );
    }
}
