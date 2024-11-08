package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Semen", "Fedotkin", (byte) 18);
        User user2 = new User("Ivan", "Fedotkin2", (byte) 28);
        User user3 = new User("Zak", "Fedotkin3", (byte) 38);
        User user4 = new User("Sanya", "Fedotkin4", (byte) 48);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        users.forEach(x -> {
                    userService.saveUser(x.getName(), x.getLastName(), x.getAge());
                    System.out.println(x.getName() + " добавлен в базу данных.");
                }
        );
        userService.getAllUsers().forEach(x -> {
            System.out.println(x.toString());
        });

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}