package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();


        User user1 = new User("Semen", "Emo", (byte) 18);
        User user2 = new User("Ivan", "Trenbolon", (byte) 28);
        User user3 = new User("Zak", "DedInside", (byte) 38);
        User user4 = new User("Sanya", "Xikka", (byte) 48);

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println(userService.getAllUsers());

        User user = userService.getAllUsers().get(0);
        userService.removeUserById(user.getId());
        userService.cleanUsersTable();

        userService.dropUsersTable();


    }
}