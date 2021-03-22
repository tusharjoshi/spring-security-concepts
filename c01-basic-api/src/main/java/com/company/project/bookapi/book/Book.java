package com.company.project.bookapi.book;

public class Book {
    private final Integer bookId;
    private final String bookName;

    public Book(Integer bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public Integer getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }
}
