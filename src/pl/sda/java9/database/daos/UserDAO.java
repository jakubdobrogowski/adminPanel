package pl.sda.java9.database.daos;

import pl.sda.java9.database.DatabaseConector;
import pl.sda.java9.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {

    public static User getUserByLogin(String login) {

        User user = null;

        try (Connection connection = DatabaseConector.getConnection()) {


            if (connection == null) {

                System.out.println("connection is null xdd");
            }

            PreparedStatement ps = connection.prepareStatement("select * from user where login = ?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            user = createUserFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private static User createUserFromResultSet(ResultSet rs) throws SQLException {

        User user = new User();

        while (rs.next()) {

            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setPassword(rs.getString("password"));
            user.setIsAdmin(rs.getInt("isAdmin"));
        }

        return user;
    }

    public static boolean saveUser(User user) {

        boolean execute = false;

        try (Connection connection = DatabaseConector.getConnection()) {

            PreparedStatement ps = connection
                    .prepareStatement("insert into user(id, name, surname, login, password, isAdmin) VALUES(null , ?, ?, ?, ?, 0)");

            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getPassword());

            execute = ps.execute();
            System.out.println(execute);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return execute;
    }
}