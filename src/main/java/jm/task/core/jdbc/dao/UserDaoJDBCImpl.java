package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final static String CREATE_USER_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS `users` ( " +
            "`id` INT NOT NULL AUTO_INCREMENT," +
            " `name` varchar(45), " +
            "`lastname` varchar(45), " +
            "`age` SMALLINT," +
            "  PRIMARY KEY(`id`))";

    private final static String INSERT_DATA_USERS = "INSERT INTO users(name,lastname,age) VALUES( ?, ?, ?)";
    private final static String DELETE_DATA_USERS = "DELETE FROM users WHERE id = ?";
    private final static String SELECT_ALL_USERS = "SELECT * FROM users";
    private final static String TRUNCATE_USERS = "TRUNCATE TABLE users";
    private final static String DROP_TABLE_USERS = "DROP TABLE IF EXISTS users";

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();

            statement.executeUpdate(CREATE_USER_TABLE_QUERY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();

            statement.execute(DROP_TABLE_USERS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DATA_USERS);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DATA_USERS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);

            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge((byte) resultSet.getInt("age"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(TRUNCATE_USERS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}