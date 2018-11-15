package ua.nure.koshova.SummaryTask4.db.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.koshova.SummaryTask4.db.dao.UserDao;
import ua.nure.koshova.SummaryTask4.db.dao.util.DatabaseRequests;
import ua.nure.koshova.SummaryTask4.db.dao.util.DatabaseUtils;
import ua.nure.koshova.SummaryTask4.db.entity.Role;
import ua.nure.koshova.SummaryTask4.db.entity.User;
import ua.nure.koshova.SummaryTask4.db.exception.CloseResourcesException;
import ua.nure.koshova.SummaryTask4.db.exception.QueryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private static final String ERROR_MESSAGE_SELECT_USER = "Can't select user by id (id = %d)";
    private static final String ERROR_MESSAGE_SELECT_USER_BY_SERIA = "Can't select user by seria (id = %s)";
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
    private static volatile UserDaoImpl instance;

    private UserDaoImpl() {

    }

    public static UserDaoImpl getInstance() {
        UserDaoImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (CarDaoImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new UserDaoImpl();
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
            DatabaseUtils.closeConnection(connection);
        }
        return userList;
    }

    public User findUser(String login, String password) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();

        User user = new User();
        Role role = new Role();
        user.setRole(role);

        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_GET_USER)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setLogin(login);
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setBlock(resultSet.getBoolean(4));
                role.setId(resultSet.getLong(5));
                role.setName(resultSet.getString(6));

            }
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_SELECT_USER_LOGIN_PASSWORD, login, password), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_SELECT_USER_LOGIN_PASSWORD, login, password), ex);
        } finally {
            DatabaseUtils.closeResultSet(resultSet);
            DatabaseUtils.closeConnection(connection);
        }
        return user;
    }

    public User findUserByPassportSeria(String seria) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();

        User user = null;

        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_USER_BY_SERIA)) {
            preparedStatement.setString(1, seria);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getLong(1));
                user.setSeria(rs.getString(2));
            }
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_SELECT_USER_BY_SERIA, seria), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_SELECT_USER_BY_SERIA, seria), ex);
        } finally {
            DatabaseUtils.closeResultSet(rs);
            DatabaseUtils.closeConnection(connection);
        }
        return user;
    }

    public User findUserById(Long id) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();

        User user = new User();

        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_USER)) {
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
            DatabaseUtils.closeConnection(connection);
        }
        return user;
    }

    public User findUserByLogin(String login) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();

        User user = null;

        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                Role role = new Role();
                user.setRole(role);
                user.setId(resultSet.getLong(1));
                user.setLogin(resultSet.getString(2));
                user.setName(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                user.setThirdName(resultSet.getString(5));
                user.setSeria(resultSet.getString(6));
                user.setPassDate(resultSet.getString(7));
                role.setId(resultSet.getLong(8));
            }
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_SELECT_USER_BY_LOGIN, login), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_SELECT_USER_BY_LOGIN, login), ex);
        } finally {
            DatabaseUtils.closeResultSet(resultSet);
            DatabaseUtils.closeConnection(connection);
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
                ps.setLong(6, user.getRole().getId());
                ps.executeUpdate();

                generatedKeys = ps.getGeneratedKeys();

                if (null != generatedKeys && generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                }

            } catch (SQLException ex) {
                LOGGER.error(ERROR_MESSAGE_CREATE_USER, ex);
                throw new QueryException(ERROR_MESSAGE_CREATE_USER, ex);
            } finally {
                if (generatedKeys != null) {
                    DatabaseUtils.closeResultSet(generatedKeys);
                }
                DatabaseUtils.closeConnection(connection);
            }
        }
        return id;
    }

    public void deleteUser(Long id) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.DELETE_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(ERROR_MESSAGE_DELETE_USER, ex);
            throw new QueryException(ERROR_MESSAGE_DELETE_USER, ex);
        } finally {
            DatabaseUtils.closeConnection(connection);
        }
    }

    public void updateUser(String thirdName,
                           String passSeria,
                           String dataPass,
                           Long id) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.UPDATE_USER)) {
            preparedStatement.setString(1, thirdName);
            preparedStatement.setString(2, passSeria);
            preparedStatement.setString(3, dataPass);
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_UPDATE_USER, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_UPDATE_USER, id), ex);
        } finally {
            DatabaseUtils.closeConnection(connection);
        }
    }

    public void updateUserRole(Long id) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SET_USER_ROLE_MANAGER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_UPDATE_USER_ROLE, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_UPDATE_USER_ROLE, id), ex);
        } finally {
            DatabaseUtils.closeConnection(connection);
        }
    }

    public void updateBlockUser(Long id) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.BLOCK_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_BLOCK_USER, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_BLOCK_USER, id), ex);
        } finally {
            DatabaseUtils.closeConnection(connection);
        }
    }

    public void updateUnblockUser(Long id) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.UNBLOCK_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_UNBLOCK_USER, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_UNBLOCK_USER, id), ex);
        } finally {
            DatabaseUtils.closeConnection(connection);
        }
    }

    public Role findRoleByUser(Long id) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();

        Role role = new Role();

        ResultSet set = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_USER_ROLE)) {
            preparedStatement.setLong(1, id);
            set = preparedStatement.executeQuery();
            if (set.next()) {
                role.setId(set.getLong(1));
                role.setName(set.getString(2));
            }
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_SELECT_USER_BY_ROLE, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_SELECT_USER_BY_ROLE, id), ex);
        } finally {
            DatabaseUtils.closeResultSet(set);
            DatabaseUtils.closeConnection(connection);
        }
        return role;
    }
}


