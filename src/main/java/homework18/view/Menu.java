package homework18.view;



import homework18.dto.BookD;
import homework18.dto.UserBaseD;
import homework18.dto.UserD;
import homework18.view.impl.UserMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public interface Menu  {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    void display();

    void getChoice();

    default String parsString() {
        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (line.equals("exit")) {
            System.exit(0);
        }
        return line;
    }

    default long parsLong() {
        String line = parsString();
        if (line.matches("\\d+")) {
            try {
                return Long.parseLong(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter only numbers!");
            }
        } else {
            System.out.println("Incorrect input");
        }
        return -1;
    }

    default int parsInt() {
        String line = parsString();
        if (line.matches("\\d+")) {
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter only numbers!");
            }
        } else {
            System.out.println("Incorrect input");
        }
        return -1;
    }

    default boolean yesOrNo() {
        while (true) {
            String answer = parsString();
            if (answer.equalsIgnoreCase("N")) {
                return false;
            } else if (answer.equalsIgnoreCase("Y")) {
                return true;
            } else {
                System.out.println("Enter 'Y' or 'N'");
            }
        }
    }

    default void printUserD(UserD user) {
        printUserBaseD(user);
        System.out.println("Books: ");
        user.getBooks().forEach(this::printBook);
    }

    default void printUserBaseDTO(UserBaseD user) {
        StringBuilder userStr = new StringBuilder();
        userStr.append("UserID : ").append(user.getId()).append("\n")
                .append("First Name : ").append(user.getFirstName()).append("\n")
                .append("Last Name : ").append(user.getLastName()).append("\n");

        System.out.println(userStr.toString());
    }

    default void printBook(BookD book) {
        StringBuilder bookStr = new StringBuilder();
        bookStr.append("BookID : ").append(book.getId()).append("\n")
                .append("Title : ").append(book.getTitle()).append("\n");
        System.out.println(bookStr.toString());
    }

    default void printUserD(List<UserBaseD> users) {
        if (users.isEmpty()) {
            System.out.println("User list is empty\n");
        } else {
            System.out.println("User list: ");
            users.forEach(this::printUserBaseDTO);
        }
    }

    default void printCurrentStep(int step, boolean canChange) {
        if (canChange) {
            switch (step) {
                case 1:
                    System.out.println("Enter first name or press Enter to skip");
                    break;
                case 2:
                    System.out.println("Enter last name or press Enter to skip");
                    break;
            }
        } else {
            switch (step) {
                case 1:
                    System.out.println("Enter first name");
                    break;
                case 2:
                    System.out.println("Enter last name");
                    break;
            }
        }
    }

    default void setUserInfo(int step, UserBaseD.UserBaseDBuilder baseDBuilder) {
        String tempStr;
        switch (step) {
            case 1:
                tempStr = parsString();
                if (!tempStr.isEmpty()) {
                    baseDBuilder.firstName(tempStr);
                }
                break;
            case 2:
                tempStr = parsString();
                if (!tempStr.isEmpty()) {
                    baseDBuilder.lastName(tempStr);
                }
                break;
        }
    }
}