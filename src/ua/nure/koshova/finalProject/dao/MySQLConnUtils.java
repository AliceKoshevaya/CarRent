package ua.nure.koshova.finalProject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {

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
            e.printStackTrace();
        }
        return conn;
    }
}