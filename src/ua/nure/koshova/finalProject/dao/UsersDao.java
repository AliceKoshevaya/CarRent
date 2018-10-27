package ua.nure.koshova.finalProject.dao;

import ua.nure.koshova.finalProject.entity.Role;
import ua.nure.koshova.finalProject.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {
    private static volatile UsersDao instance;

    private UsersDao() {

    }

    public static UsersDao getInstance() {
        UsersDao localInstance = instance;
        if (localInstance == null) {
            synchronized (CarsDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new UsersDao();
                }
            }
        }
        return localInstance;
    }

    public static void main(String[] args) throws SQLException {
        UsersDao u = new UsersDao();
        System.out.println(u.findAllUsers());
        Role role = new Role();
        role.setName("manager");
        System.out.println(u.findUsersByRole(role));
        User user = new User();
        u.createUser(user);
    }

    public List<User> findAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        Connection connection = MySQLConnUtils.getMySQLConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select u.id, u.user_login, u.password, u.first_name, u.last_name, " +
                "u.third_name, u.pass_seria,\n" +
                "u.data_pass, u.id_role, r.role_name from users AS u left join roles AS r ON u.id_role = r.id;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = new User();
            Role role = new Role();
            user.setRole(role);
            user.setId(resultSet.getLong(1));
            user.setLogin(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setName(resultSet.getString(4));
            user.setLastName(resultSet.getString(5));
            user.setThirdName(resultSet.getString(6));
            user.setSeria(resultSet.getString(7));
            user.setPassDate(resultSet.getString(8));
            role.setId(resultSet.getLong(9));
            role.setName(resultSet.getString(10));
            userList.add(user);
        }
        return userList;
    }

    public List<User> findUsersByRole(Role role) throws SQLException {
        List<User> userList = new ArrayList<>();
        Connection connection = MySQLConnUtils.getMySQLConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select u.id, u.user_login,u.password, u.first_name, " +
                "u.last_name, u.third_name, u.pass_seria, u.data_pass FROM\n" +
                "users as u WHERE id_role = 3;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = new User();
            user.setRole(role);
            user.setId(resultSet.getLong(1));
            user.setLogin(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setName(resultSet.getString(4));
            user.setLastName(resultSet.getString(5));
            user.setThirdName(resultSet.getString(6));
            user.setSeria(resultSet.getString(7));
            user.setPassDate(resultSet.getString(8));
            userList.add(user);
        }
        return userList;
    }

    public void updateUserRole(User user) throws SQLException {
        Connection connection = MySQLConnUtils.getMySQLConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET id_role=? WHERE id=?");
        preparedStatement.setLong(2, user.getId());
//        preparedStatement.setString(1, user.getRole());
        preparedStatement.executeUpdate();
    }

    public User findUser(String login, String password) throws SQLException {
        Connection connection = MySQLConnUtils.getMySQLConnection();
        String sql = "select u.first_name, u.last_name, u.third_name, u.pass_seria, u.data_pass FROM\n" +
                "users as u WHERE u.user_login=? and u.password=?; ";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, login);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setLogin(login);
            user.setName(rs.getString(1));
            user.setLastName(rs.getString(2));
            user.setThirdName(rs.getString(3));
            user.setSeria(rs.getString(4));
            user.setPassDate(rs.getString(5));
            return user;
        }
        return null;
    }

    public Long createUser(User user) {
        Long id = null;
        Connection connection = MySQLConnUtils.getMySQLConnection();
        if (user != null) {
            try (PreparedStatement ps =
                         connection.prepareStatement("insert into users values (DEFAULT ,?,?,?,?,?,?,?,?);",
                                 Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getName());
                ps.setString(4, user.getLastName());
                ps.setString(5, user.getThirdName());
                ps.setString(6, user.getSeria());
                ps.setString(7, user.getPassDate());
                ps.setLong(8, user.getRole().getId());
                ps.executeUpdate();

                ResultSet generatedKeys = ps.getGeneratedKeys();

                if (null != generatedKeys && generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                }

            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
        return id;
    }
}


