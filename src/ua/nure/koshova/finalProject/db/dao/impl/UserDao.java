package ua.nure.koshova.finalProject.db.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.koshova.finalProject.db.dao.IUserDao;
import ua.nure.koshova.finalProject.db.dao.util.DatabaseRequests;
import ua.nure.koshova.finalProject.db.dao.util.DatabaseUtils;
import ua.nure.koshova.finalProject.db.entity.Role;
import ua.nure.koshova.finalProject.db.entity.User;
import ua.nure.koshova.finalProject.db.exception.CloseResourcesException;
import ua.nure.koshova.finalProject.db.exception.QueryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDao.class);
    private static volatile UserDao instance;

    private static final String ERROR_MESSAGE_SELECT_USER = "Can't select user by id (id = %d)";
    private static final String ERROR_MESSAGE_SELECT_USER_BY_LOGIN = "Can't select user by login (login = %s)";
    private static final String ERROR_MESSAGE_UPDATE_USER = "Can't update user details by id(id = %d)";
    private static final String ERROR_MESSAGE_UPDATE_USER_ROLE = "Can't update user role by id(id = %d)";
    private static final String ERROR_MESSAGE_BLOCK_USER = "Can't update user status on blocked by id(id = %d)";
    private static final String ERROR_MESSAGE_UNBLOCK_USER = "Can't update user status on unblocked by id(id = %d)";
    private static final String ERROR_MESSAGE_SELECT_USER_BY_ROLE = "Can't select user by role (id = %d)";
    private static final String ERROR_MESSAGE_SELECT_USER_LOGIN_PASSWORD = "Can't select user by login and password (login = %spassword = %s)";
    private static final String ERROR_MESSAGE_CREATE_USER = "Can't create a new user";
    private static final String ERROR_MESSAGE_DELETE_USER = "Can't delete a user";
    private static final String ERROR_MESSAGE_SELECT_ALL_USERS = "Can't select all users";

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
        Connection connection = DatabaseUtils.getConnection();

        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_ALL_USERS)) {
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
            DatabaseUtils.closeResultSet(resultSet);
        }
        return userList;
    }

    public User findUser(String login, String password) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();

        User user = new User();
        Role role = new Role();

        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_GET_USER)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {

                user.setRole(role);
                user.setLogin(login);
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                role.setId(rs.getLong(4));
                role.setName(rs.getString(5));

            }
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_SELECT_USER_LOGIN_PASSWORD, login, password), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_SELECT_USER_LOGIN_PASSWORD, login, password), ex);
        } finally {
            DatabaseUtils.closeResultSet(rs);
        }
        return user;
    }


    public User findUserById(Long id) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();

        User user = new User();

        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.CHECKBLOCK)) {
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user.setId(rs.getLong(1));
                user.setBlock(rs.getBoolean(2));
            }
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_SELECT_USER, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_SELECT_USER, id), ex);
        } finally {
            DatabaseUtils.closeResultSet(rs);
        }
        return user;
    }

    public User findUserByLogin(String login) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();

        User user = new User();

        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            rs = preparedStatement.executeQuery();
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
            LOGGER.error(String.format(ERROR_MESSAGE_SELECT_USER_BY_LOGIN, login), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_SELECT_USER_BY_LOGIN, login), ex);
        } finally {
            DatabaseUtils.closeResultSet(rs);
        }
        return user;
    }

    public Long createUser(User user) throws QueryException, CloseResourcesException {
        Long id = null;
        Connection connection = DatabaseUtils.getConnection();

        ResultSet generatedKeys = null;
        if (user != null) {
            try (PreparedStatement ps =
                         connection.prepareStatement(DatabaseRequests.INSERT_USER,
                                 Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getName());
                ps.setString(4, user.getLastName());
                ps.setString(5, user.getThirdName());
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
        Connection con = DatabaseUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(DatabaseRequests.DELETE_USER)) {
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
        Connection con = DatabaseUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(DatabaseRequests.UPDATE_USER)) {
            preparedStatement.setString(1, thirdName);
            preparedStatement.setString(2, passSeria);
            preparedStatement.setString(3, dataPass);
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_UPDATE_USER, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_UPDATE_USER, id), ex);
        }
    }

    public void updateUserRole(Long id) throws QueryException, CloseResourcesException {
        Connection con = DatabaseUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(DatabaseRequests.SET_A_MANAGER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_UPDATE_USER_ROLE, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_UPDATE_USER_ROLE, id), ex);
        }
    }

    public void updateBlockUser(Long id) throws QueryException, CloseResourcesException {
        Connection con = DatabaseUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(DatabaseRequests.BLOCK_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_BLOCK_USER, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_BLOCK_USER, id), ex);
        }
    }

    public void updateUnblockUser(Long id) throws QueryException, CloseResourcesException {
        Connection con = DatabaseUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(DatabaseRequests.UNBLOCK_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_UNBLOCK_USER, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_UNBLOCK_USER, id), ex);
        }
    }

    public Role findRoleByUser(Long id) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();

        Role role = new Role();

        ResultSet rs = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_USER_ROLE)) {
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                role.setId(rs.getLong(1));
                role.setName(rs.getString(2));
            }
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_SELECT_USER_BY_ROLE, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_SELECT_USER_BY_ROLE, id), ex);
        } finally {
            DatabaseUtils.closeResultSet(rs);
        }
        return role;
    }
}


