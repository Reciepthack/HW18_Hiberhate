package com.homework18.repository;

import com.homework18.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void createBook(Book book) {
        getCurrentSession().persist(book);
    }

    @Transactional
    public Book getBookById(Long bookId) {
        return getCurrentSession().get(Book.class, bookId);
    }

    @Transactional
    public List<Book> getAllBooks() {
        return getCurrentSession().createQuery("Select b From Book b").list();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
