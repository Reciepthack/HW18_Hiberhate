package com.homework18.service;


import com.homework18.model.Book;
import com.homework18.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void insertBooks() {
        createBook(new Book("The Swordless Samurai", "Kitami Masao"));
        createBook(new Book("Art of War", "Sun-ji"));
        createBook(new Book("The Book of Five Rings", "Miyamoto Musashi"));
        createBook(new Book("Golden Temple", "Yukio Mishima"));
    }

    public void createBook(Book book) {
        bookRepository.createBook(book);
    }

    public Book getBookById(Long bookId) {
        return bookRepository.getBookById(bookId);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }
}
