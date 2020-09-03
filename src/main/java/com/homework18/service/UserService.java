package com.homework18.service;


import com.homework18.model.Book;
import com.homework18.model.User;
import com.homework18.repository.BookRepository;
import com.homework18.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    public void insertUsers() {
        createUser(new User("Ganry", "Cavil", 35, "+83737291710", new ArrayList<>()));
        createUser(new User("Criss", "Pratt", 48, "+86327641684", new ArrayList<>()));
        createUser(new User("Criss", "Evans", 51, "+98741691479", new ArrayList<>()));
        createUser(new User("Rayan", "Reynolds", 34, "+87814581665", new ArrayList<>()));
    }

    public void createUser(User user) {
        userRepository.createUser(user);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    public void deleteUser(Long userId) {
        User user = getUserById(userId);

        userRepository.deleteUser(user);
    }

    public void editUser(Long userId, String firstName, String lastName, Integer age, String number) {
        User user = getUserById(userId);

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setNumber(number);

        userRepository.editUser(user);
    }

    public void addBookToUser(Long userId, Long bookId) {
        User user = getUserById(userId);
        Book book = bookRepository.getBookById(bookId);

        List<Book> userBooks = user.getBookList();
        userBooks.add(book);
        book.setOwner(user);
        userRepository.editUser(user);
    }

    public List<Book> getAllUserBooks(Long userId) {
        return getUserById(userId).getBookList();
    }


}
