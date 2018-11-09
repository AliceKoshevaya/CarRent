package ua.nure.koshova.finalProject.db.dao.impl;

import ua.nure.koshova.finalProject.db.dao.IUserDao;
import ua.nure.koshova.finalProject.db.dao.util.MySQLConnUtils;
import ua.nure.koshova.finalProject.db.dao.util.RequestsToDB;
import ua.nure.koshova.finalProject.db.entity.Role;
import ua.nure.koshova.finalProject.db.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {
    private static volatile UserDao instance;

    private UserDao() {

    }

    public static UserDao getInstance() {
        UserDao localInstance = instance;
        if (localInstance == null) {
            synchronized (CarDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new UserDao();
                }
            }
        }
        return localInstance;
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
                user.setBlock(resultSet.getBoolean(11));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User findUser(String login, String password){
        Connection connection = MySQLConnUtils.getMySQLConnection();
        User user = new User();
        try {
            PreparedStatement pstm = connection.prepareStatement(RequestsToDB.SELECT_GET_USER);
            pstm.setString(1, login);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {

                user.setLogin(login);
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setThirdName(rs.getString(4));
                user.setSeria(rs.getString(5));
                user.setPassDate(rs.getString(6));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public User findUserById(Long id){
        Connection connection = MySQLConnUtils.getMySQLConnection();
        User user = new User();
        try {
            PreparedStatement pstm = connection.prepareStatement(RequestsToDB.CHECKBLOCK);
            pstm.setLong(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                user.setId(rs.getLong(1));
                user.setBlock(rs.getBoolean(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public User findUserByLogin(String login){
        Connection connection = MySQLConnUtils.getMySQLConnection();
        User user = new User();
        try {
            PreparedStatement pstm = connection.prepareStatement(RequestsToDB.SELECT_USER_BY_LOGIN);
            pstm.setString(1, login);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Role role = new Role();
                user.setRole(role);
                user.setId(rs.getLong(1));
                user.setLogin(rs.getString(2));
                user.setName(rs.getString(3));
                user.setLastName(rs.getString(4));
                user.setThirdName(rs.getString(5));
                user.setSeria(rs.getString(6));
                user.setPassDate(rs.getString(7));
                role.setId(rs.getLong(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
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

    public void updateBlockUser(Long id) {
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.BLOCK_USER);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUnblockUser(Long id) {
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.UNBLOCK_USER);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Role findUserByRole(Long id){
        Connection connection = MySQLConnUtils.getMySQLConnection();
        User user = new User();
        Role role = new Role();
        try {
            PreparedStatement pstm = connection.prepareStatement(RequestsToDB.SELECT_USER_ROLE);
            pstm.setLong(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                user.setRole(role);
                role.setId(rs.getLong(1));
                role.setName(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}


