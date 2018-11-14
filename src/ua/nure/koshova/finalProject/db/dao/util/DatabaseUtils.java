package ua.nure.koshova.finalProject.db.dao.util;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import ua.nure.koshova.finalProject.db.exception.CloseResourcesException;
import ua.nure.koshova.finalProject.db.exception.DatabaseConnectionException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Used to establish database connections.
 */
public class DatabaseUtils {

    private static final Logger LOGGER = Logger.getLogger(DatabaseUtils.class);

    private static final String MESSAGE_ERROR_DB_CONN = "Can't establish connection with database";
    private static final String MESSAGE_ERROR_CLOSE_RESULT_SET = "Unable to close result set";
    private static final String MESSAGE_ERROR_CLOSE_CONNECTION = "Unable to close connection";

    private static BasicDataSource dataSource;

    private static void initDataSource(String connectionURL,
                                       String userName,
                                       String password,
                                       String driver) {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(connectionURL);
        ds.setUsername(userName);
        ds.setPassword(password);
        ds.setDriverClassName(driver);

        dataSource = ds;
    }

    private static Connection getConnectionFromDatasource() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error(MESSAGE_ERROR_DB_CONN, e);
            throw new DatabaseConnectionException(MESSAGE_ERROR_DB_CONN, e);
        }
    }

    public static void initDataSource() {
        Properties property = new Properties();
        try {
            ClassLoader classLoader = DatabaseUtils.class.getClassLoader();
            property.load(classLoader.getResourceAsStream("config.properties"));
            String connectionURL = property.getProperty("db.host");
            String userName = property.getProperty("db.login");
            String password = property.getProperty("db.password");
            String driver = property.getProperty("db.driver");
            initDataSource(connectionURL, userName, password, driver);
        } catch (IOException | NullPointerException e) {
            LOGGER.error("An error has occurred! Connection data was not received", e);
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return getConnectionFromDatasource();
    }

    public static void closeResultSet(ResultSet resultSet) throws CloseResourcesException {
        try {
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error(MESSAGE_ERROR_CLOSE_RESULT_SET, e);
            throw new CloseResourcesException(MESSAGE_ERROR_CLOSE_RESULT_SET, e);
        }
    }

    public static void closeConnection(Connection connection) throws CloseResourcesException {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(MESSAGE_ERROR_CLOSE_CONNECTION, e);
            throw new CloseResourcesException(MESSAGE_ERROR_CLOSE_CONNECTION, e);
        }
    }
}