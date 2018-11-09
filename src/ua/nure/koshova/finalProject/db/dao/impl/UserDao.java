package ua.nure.koshova.finalProject.db.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.koshova.finalProject.db.dao.IUserDao;
import ua.nure.koshova.finalProject.db.dao.util.MySQLConnUtils;
import ua.nure.koshova.finalProject.db.dao.util.RequestsToDB;
import ua.nure.koshova.finalProject.db.entity.Role;
import ua.nure.koshova.finalProject.db.entity.User;
import ua.nure.koshova.finalProject.db.exception.CloseResourcesException;
import ua.nure.koshova.finalProject.db.exception.QueryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {

    public static final String ERROR_MESSAGE_SELECT_ALL_USERS = "Can't select all users";
    public static final String ERROR_MESSAGE_CREATE_USER = "Can't create a new user";
    public static final String ERROR_MESSAGE_DELETE_USER = "Can't delete a user";
    private static final Logger LOGGER = Logger.getLogger(UserDao.class);
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

    public List<User> findAllUsers() throws QueryException, CloseResourcesException {
        List<User> userList = new ArrayList<>();
        Connection connection = MySQLConnUtils.getMySQLConnection();

        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(RequestsToDB.SELECT_ALL_USERS);
            resultSet = preparedStatement.executeQuery();
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
        } catch (SQLException ex) {
            LOGGER.error(ERROR_MESSAGE_SELECT_ALL_USERS, ex);
            throw new QueryException(ERROR_MESSAGE_SELECT_ALL_USERS, ex);
        } finally {
            MySQLConnUtils.closeResultSet(resultSet);
        }
        return userList;
    }

    public User findUser(String login, String password) throws QueryException, CloseResourcesException {
        Connection connection = MySQLConnUtils.getMySQLConnection();

        User user = new User();

        ResultSet rs = null;
        try {
            PreparedStatement pstm = connection.prepareStatement(RequestsToDB.SELECT_GET_USER);
            pstm.setString(1, login);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            if (rs.next()) {

                user.setLogin(login);
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setThirdName(rs.getString(4));
                user.setSeria(rs.getString(5));
                user.setPassDate(rs.getString(6));

            }
        } catch (SQLException ex) {
            LOGGER.error("Can't select user by login and password (login = " + login
                    + "password = " + password + ")", ex);
            throw new QueryException("Can't select user by login and password (login = " + login
                    + "password = " + password + ")", ex);
        } finally {
            MySQLConnUtils.closeResultSet(rs);
        }
        return user;
    }


    public User findUserById(Long id) throws QueryException, CloseResourcesException {
        Connection connection = MySQLConnUtils.getMySQLConnection();

        User user = new User();

        ResultSet rs = null;
        try {
            PreparedStatement pstm = connection.prepareStatement(RequestsToDB.CHECKBLOCK);
            pstm.setLong(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                user.setId(rs.getLong(1));
                user.setBlock(rs.getBoolean(2));
            }
        } catch (SQLException ex) {
            LOGGER.error("Can't select user by id (id = " + id + ")", ex);
            throw new QueryException("Can't select user by id (id = " + id + ")", ex);
        } finally {
            MySQLConnUtils.closeResultSet(rs);
        }
        return user;
    }

    public User findUserByLogin(String login) throws QueryException, CloseResourcesException {
        Connection connection = MySQLConnUtils.getMySQLConnection();

        User user = new User();

        ResultSet rs = null;
        try {
            PreparedStatement pstm = connection.prepareStatement(RequestsToDB.SELECT_USER_BY_LOGIN);
            pstm.setString(1, login);
            rs = pstm.executeQuery();
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
        } catch (SQLException ex) {
            LOGGER.error("Can't select user by login (login = " + login + ")", ex);
            throw new QueryException("Can't select user by login (login = " + login + ")", ex);
        } finally {
            MySQLConnUtils.closeResultSet(rs);
        }
        return user;
    }

    public Long createUser(User user) throws QueryException, CloseResourcesException {
        Long id = null;
        Connection connection = MySQLConnUtils.getMySQLConnection();

        ResultSet generatedKeys = null;
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

                generatedKeys = ps.getGeneratedKeys();

                if (null != generatedKeys && generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                }

            } catch (SQLException ex) {
                LOGGER.error(ERROR_MESSAGE_CREATE_USER, ex);
                throw new QueryException(ERROR_MESSAGE_CREATE_USER, ex);
            }
        }
        return id;
    }

    public void deleteUser(Long id) throws QueryException, CloseResourcesException {
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.DELETE_USER);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(ERROR_MESSAGE_DELETE_USER, ex);
            throw new QueryException(ERROR_MESSAGE_DELETE_USER, ex);
        }
    }

    public void updateUser(String thirdName,
                           String passSeria,
                           String dataPass,
                           Long id) throws QueryException, CloseResourcesException {
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.UPDATE_USER);
            preparedStatement.setString(1, thirdName);
            preparedStatement.setString(2, passSeria);
            preparedStatement.setString(3, dataPass);
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error("Can't update user details by id(id = " + id + ")", ex);
            throw new QueryException("Can't update user details by id(id = " + id + ")", ex);
        }
    }

    public void updateUserRole(Long id) throws QueryException, CloseResourcesException {
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.SET_A_MANAGER);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error("Can't update user role by id(id = " + id + ")", ex);
            throw new QueryException("Can't update user role by id(id = " + id + ")", ex);
        }
    }

    public void updateBlockUser(Long id) throws QueryException, CloseResourcesException {
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.BLOCK_USER);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error("Can't update user status on blocked by id(id = " + id + ")", ex);
            throw new QueryException("Can't update user status on blocked by id(id = " + id + ")", ex);
        }
    }

    public void updateUnblockUser(Long id) throws QueryException, CloseResourcesException {
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.UNBLOCK_USER);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error("Can't update user status on unblocked by id(id = " + id + ")", ex);
            throw new QueryException("Can't update user status on unblocked by id(id = " + id + ")", ex);
        }
    }

    public Role findUserByRole(Long id) throws QueryException, CloseResourcesException {
        Connection connection = MySQLConnUtils.getMySQLConnection();

        User user = new User();
        Role role = new Role();

        ResultSet rs = null;

        try {
            PreparedStatement pstm = connection.prepareStatement(RequestsToDB.SELECT_USER_ROLE);
            pstm.setLong(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                user.setRole(role);
                role.setId(rs.getLong(1));
                role.setName(rs.getString(2));
            }
        } catch (SQLException ex) {
            LOGGER.error("Can't select user by role (id = " + id + ")", ex);
            throw new QueryException("Can't select user by role (id = " + id + ")", ex);
        } finally {
            MySQLConnUtils.closeResultSet(rs);
        }
        return role;
    }
}


