package com.homework18.view;


import com.homework18.model.User;
import com.homework18.service.UserService;
import com.homework18.service.BookService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class Menu {
    private static final String USER_ID = "Enter user ID: ";

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    private static final String PAUSE_MASSAGE = "Press Enter to continue";
    private final String[] menuItems = {
            "\t\tMENU:",
            "1. Show all users",
            "2. Show user by ID",
            "3. Add user",
            "4. Delete user",
            "5. Edit user",
            "6. Add book to user",
            "7. Show all user books",
            "0. Exit"
    };

    public void showMenuItems(String[] menu) {
        Arrays.asList(menu).forEach(System.out::println);
    }
    public int enterChoice() throws IOException {
        showMenuItems(menuItems);
        System.out.print("Enter a number, please: ");
        return Integer.parseInt(inputReader());
    }

    @SneakyThrows
    public String inputReader() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (reader != null) {
            input.close();
            reader.close();
        }
        return "";
    }

    public void pauseConsol() throws IOException {
        System.out.println(PAUSE_MASSAGE);
        inputReader();
    }

    public void showAllUsers() throws IOException {
        System.out.println("\t\tLIST OF USERS");
        userService.getAllUsers().forEach(System.out::println);
        pauseConsol();
        runMenu();
    }

    public void showUserById() throws IOException {
        System.out.print(USER_ID);
        Long userId = Long.parseLong(inputReader());
        System.out.println(userService.getUserById(userId));
        pauseConsol();
        runMenu();
    }

    public void createUser() throws IOException {
        System.out.print("First Name: ");
        String firstName = inputReader();

        System.out.print("Last Name: ");
        String lastName = inputReader();

        System.out.print("Age: ");
        Integer age = Integer.parseInt(inputReader());

        System.out.print("Phone Number: ");
        String number = inputReader();

        userService.createUser(new User(firstName, lastName, age, number, new ArrayList<>()));
        pauseConsol();
        runMenu();
    }

    public void deleteUser() throws IOException {
        System.out.print(USER_ID);
        Long userId = Long.parseLong(inputReader());

        userService.deleteUser(userId);
        pauseConsol();
        runMenu();
    }

    public void editUser() throws IOException {
        System.out.print(USER_ID);
        Long userId = Long.parseLong(inputReader());

        System.out.print("New First Name: ");
        String firstName = inputReader();

        System.out.print("New Last Name: ");
        String lastName = inputReader();

        System.out.print("New Age: ");
        Integer age = Integer.parseInt(inputReader());

        System.out.print("New Phone Number: ");
        String number = inputReader();

        userService.editUser(userId, firstName, lastName, age, number);
        pauseConsol();
        runMenu();
    }

    public void addBookToUser() throws IOException {
        System.out.print(USER_ID);
        Long userId = Long.parseLong(inputReader());

        System.out.println("\t\tLIST OF BOOKS");
        bookService.getAllBooks().forEach(System.out::println);

        System.out.print("Book ID: ");
        Long bookId = Long.parseLong(inputReader());

        userService.addBookToUser(userId, bookId);
        pauseConsol();
        runMenu();
    }

    public void showAllUserBooks() throws IOException {
        System.out.print(USER_ID);
        Long userId = Long.parseLong(inputReader());

        System.out.println("\t\tLIST OF USER BOOKS");
        userService.getAllUserBooks(userId).forEach(System.out::println);
        pauseConsol();
        runMenu();
    }

    public void showDefaultInMenu() throws IOException {
        System.out.println("Wrong input, try one more time");
        runMenu();
    }

    public void runMenu() throws IOException {
        switch (enterChoice()) {
            case 1 -> showAllUsers();
            case 2 -> showUserById();
            case 3 -> createUser();
            case 4 -> deleteUser();
            case 5 -> editUser();
            case 6 -> addBookToUser();
            case 7 -> showAllUserBooks();
            case 0 -> System.exit(0);
            default -> showDefaultInMenu();
        }
    }
}