package ua.nure.koshova.finalProject.db.dao;

import ua.nure.koshova.finalProject.db.dao.util.MySQLConnUtils;
import ua.nure.koshova.finalProject.db.dao.util.RequestsToDB;
import ua.nure.koshova.finalProject.db.entity.Role;
import ua.nure.koshova.finalProject.db.entity.User;

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

    public static void main(String[] args){
        UsersDao u = new UsersDao();
        System.out.println(u.findAllUsers());
        Role role = new Role();
        role.setName("manager");
        System.out.println(u.findUsersByRole(role));
        System.out.println(u.findUserByLogin("alice777"));
    }

    public List<User> findAllUsers(){
        List<User> userList = new ArrayList<>();
        Connection connection = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(RequestsToDB.SELECT_ALL_USERS);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public List<User> findUsersByRole(Role role) {
        List<User> userList = new ArrayList<>();
        Connection connection = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(RequestsToDB.SELECT_USER_BY_ROLE);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public boolean findUser(String login, String password){
        Connection connection = MySQLConnUtils.getMySQLConnection();
        boolean isUser = false;
        try {
            PreparedStatement pstm = connection.prepareStatement(RequestsToDB.SELECT_GET_USER);
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
                isUser = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUser;
    }
    public Long findUserByLogin(String login){
        Connection connection = MySQLConnUtils.getMySQLConnection();
        Long id = null;
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT users.id FROM users WHERE users.user_login = ?");
            pstm.setString(1, login);
            ResultSet rs = pstm.executeQuery();
            User user = new User();
            if (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    public Long createUser(User user) {
        Long id = null;
        Connection connection = MySQLConnUtils.getMySQLConnection();
        if (user != null) {
            try (PreparedStatement ps =
                         connection.prepareStatement(RequestsToDB.INSERT_USER,
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

    public void deleteUser(Long id) {
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.DELETE_USER);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(String thirdName,
                           String passSeria,
                           String dataPass,
                           Long id) {
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.UPDATE_USER);
            preparedStatement.setString(1, thirdName);
            preparedStatement.setString(2, passSeria);
            preparedStatement.setString(3, dataPass);
            preparedStatement.setLong(4,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserRole(Long id) {
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.SET_A_MANAGER);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


