package ua.nure.koshova.finalProject.db.dao.impl;

import ua.nure.koshova.finalProject.db.dao.IOrderDao;
import ua.nure.koshova.finalProject.db.dao.util.MySQLConnUtils;
import ua.nure.koshova.finalProject.db.dao.util.RequestsToDB;
import ua.nure.koshova.finalProject.db.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IOrderDao {
    private static volatile OrderDao instance;

    private OrderDao(){

    }

    public static OrderDao getInstance() {
        OrderDao localInstance = instance;
        if (localInstance == null) {
            synchronized (CarDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new OrderDao();
                }
            }
        }
        return localInstance;
    }
    public Long createOrder(Order order) {
        Long id = null;
        Connection con = MySQLConnUtils.getMySQLConnection();
        if (order != null) {
            try (PreparedStatement ps = con.prepareStatement(RequestsToDB.INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
                ps.setBoolean(1, order.isDriver());
                ps.setString(2, order.getStatus().toString());
                ps.setTimestamp(3, order.getStartRent());
                ps.setTimestamp(4, order.getEndRent());
                ps.setLong(5, order.getUser().getId());
                ps.setLong(6, order.getCar().getId());
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

    public List<Order> findAllOrders(){
        List<Order> orderList = new ArrayList<>();
        Connection connection = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(RequestsToDB.SELECT_ALL_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                User user = new User();
                Car car = new Car();
                order.setUser(user);
                order.setCar(car);
                order.setId(resultSet.getLong(1));
                order.setDriver(resultSet.getBoolean(2));
                order.setStatus(OrderStatus.valueOf(resultSet.getString(3).toUpperCase()));
                order.setStartRent(resultSet.getTimestamp(4));
                order.setEndRent(resultSet.getTimestamp(5));
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public void updateConfirmOrder(Long id) {
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.CONFIRM_ORDER);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateCrashOrder(Long id) {
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.CRASH_ORDER);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateCloseOrder(Long id) {
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.CLOSE_ORDER);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateReasonOrder(Long id, String reason) {
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.UPDATE_REASON);
            preparedStatement.setString(1,reason);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
