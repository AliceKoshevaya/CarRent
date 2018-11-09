package ua.nure.koshova.finalProject.db.dao.util;

import org.apache.log4j.Logger;
import ua.nure.koshova.finalProject.db.exception.CloseResourcesException;

import java.sql.*;

/**
 * Used to establish database connections.
 */
public class MySQLConnUtils {

    private static final Logger LOGGER = Logger.getLogger(MySQLConnUtils.class);

    public static Connection getMySQLConnection() {
        String hostName = "localhost";
        String dbName = "car_rent";
        String userName = "root";
        String password = "Лемур21195";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionURL, userName,
                    password);
        } catch (SQLException e) {
            LOGGER.error("Can't establish connection with MySQL", e);
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeResultSet(ResultSet resultSet) throws CloseResourcesException{
        try {
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error("Can't close result set", e);
            throw new CloseResourcesException("An error occurred while closing result set",e);
        }
    }
}