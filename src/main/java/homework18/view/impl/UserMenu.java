package homework18.view.impl;

import homework18.dto.BookD;
import homework18.dto.UserBaseD;
import homework18.dto.UserD;
import homework18.service.UserService;
import homework18.view.Menu;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


@Component
public class UserMenu implements Menu, InitializingBean {
    private final UserService userService;

    public UserMenu(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void afterPropertiesSet() {
        display();
    }

    @Override
    public void display() {
        StringBuilder userMenu = new StringBuilder();
        userMenu.append("[1] - Create User\n")
                .append("[2] - Add Book To User\n")
                .append("[3] - Update User\n")
                .append("[4] - Print All Users\n")
                .append("[5] - Delete User\n")
                .append("[6] - Print User By ID\n")
                .append("[7] - Print User And Books By ID\n\n")
                .append("Please select item.../\\ ");
        while (true) {
            System.out.print(userMenu.toString());
            getChoice();
        }
    }

    @Override
    public void getChoice() {
        try {
            switch (parsInt()) {
                case 1 -> createUser();
                case 2 -> addBookToUser();
                case 3 -> updateUsers();
                case 4 -> printAllUsers();
                case 5 -> deleteUsers();
                case 6 -> printUserByID();
                case 7 -> printBooksByUserID();
                default -> throw new IllegalArgumentException("Incorrect input");
            }
        } catch (IllegalArgumentException ignored) {
        }
    }

    void createUser() {
        UserBaseD.UserBaseDBuilder dtoBuilder = UserBaseD.builder();
        for (int i = 1; i < 4; i++) {
            printCurrentStep(i, false);
            setUserInfo(i, dtoBuilder);
        }
        UserBaseD userBaseD = dBuilder.build();
        userService.createUser(userBaseD, UserBaseD.class);
    }

    void addBookToUser() {
        System.out.println("Select userID");
        long id = parsLong();
        if (userService.checkExistsById(id)) {
            UserD userD = userService.findUserByIdJoinFetch(id, UserD.class);
            BookD bookDTO = new BookD();
            System.out.println("Enter title");
            String tempStr = parsString();
            if (!tempStr.isEmpty()) {
                bookDTO.setTitle(tempStr);
            }
            bookD.setOwner(userD);
            userD.getBooks().add(bookDTO);
            userService.update(userD,UserD.class);
        }else {
            System.out.println("User Not Found \n");
        }
    }

    void updateUsers() {
        printAllUsers();
        System.out.println("Select userID do you want to edit  ? ");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Do you want edit another one? Y or N");
        do {
            updateUser();
            System.out.println(stringBuilder.toString());
        } while (yesOrNo());
    }

    void updateUser() {
        long id = parsLong();
        if (userService.checkExistsById(id)) {
            UserBaseD.UserBaseDBuilder dtoBuilder = UserBaseD.builder();
            UserBaseD dFromDB = userService.findUserById(id, UserBaseD.class);
            do {
                for (int i = 1; i < 4; i++) {
                    printCurrentStep(i, true);
                    setUserInfo(i, dtoBuilder);
                }
                UserBaseD d = dBuilder.build();
                if (d.getFirstName() != null && !d.getFirstName().isEmpty()) {
                    dFromDB.setFirstName(d.getFirstName());
                }
                if (d.getLastName() != null && !d.getLastName().isEmpty()) {
                    dFromDB.setLastName(d.getLastName());
                }

                System.out.println("Please check you in.../");
                printUserBaseD(dFromDB);
                System.out.println("Please press Y if you want save, or N to change user...");
            } while (!yesOrNo());
            userService.update(dFromDB, UserBaseD.class);
        } else {
            System.out.println("User Not Found");
        }
    }

    void deleteUsers() {
        printAllUsers();
        System.out.println("Select userID do you want to delete  ? ");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Do you want delete another one? Y or N");
        do {
            deleteUser();
            System.out.println(stringBuilder.toString());
        } while (yesOrNo());
    }

    void deleteUser() {
        long id = parsLong();
        if (userService.checkExistsById(id)) {
            if (userService.deleteById(id) > 0) {
                System.out.println("User deleted");
            } else {
                System.out.println("Error user NOT deleted");
            }
        } else {
            System.out.println("User Not Found");
        }
    }

    void printAllUsers() {
        printUserD(userService.findAllUsers(UserBaseD.class));
    }

    void printUserByID() {
        System.out.println("Please enter userID");
        long id = parsLong();
        if (userService.checkExistsById(id)) {
            UserBaseD dFromDB = userService.findUserById(id, UserBaseD.class);
            printUserBaseDTO(dFromDB);
        } else {
            System.out.println("User Not Found");
        }
    }

    void printBooksByUserID() {
        System.out.println("Please enter userID");
        long id = parsLong();
        if (userService.checkExistsById(id)) {
            UserD dFromDB = userService.findUserByIdJoinFetch(id, UserD.class);
            printUserD(dFromDB);
        } else {
            System.out.println("User Not Found");
        }
    }
}