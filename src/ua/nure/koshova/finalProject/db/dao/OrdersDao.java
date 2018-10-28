package ua.nure.koshova.finalProject.db.dao;

import ua.nure.koshova.finalProject.db.dao.util.MySQLConnUtils;
import ua.nure.koshova.finalProject.db.dao.util.Requests;
import ua.nure.koshova.finalProject.db.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDao {
    private static volatile OrdersDao instance;

    private OrdersDao(){

    }

    public static OrdersDao getInstance() {
        OrdersDao localInstance = instance;
        if (localInstance == null) {
            synchronized (CarsDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new OrdersDao();
                }
            }
        }
        return localInstance;
    }
    public Long createOrder(Order order) {
        Long id = null;
        Connection con = MySQLConnUtils.getMySQLConnection();
        if (order != null) {
            try (PreparedStatement ps = con.prepareStatement(Requests.INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
                ps.setBoolean(1, order.isDriver());
                ps.setTimestamp(2, order.getStartRent());
                ps.setTimestamp(3, order.getEndRent());
                ps.setLong(4, order.getUser().getId());
                ps.setLong(5, order.getBill().getId());
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
        List<Order> orderList = new ArrayList<Order>();
        Connection connection = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Requests.SELECT_ALL_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                Bill bill = new Bill();
                User user = new User();
                Car car = new Car();
                order.setBill(bill);
                order.setUser(user);
                order.setId(resultSet.getLong(1));
                order.setDriver(resultSet.getBoolean(2));
                order.setStartRent(resultSet.getTimestamp(3));
                order.setEndRent(resultSet.getTimestamp(4));
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }



    public static void main(String[] args) throws SQLException {
        OrdersDao o = new OrdersDao();
        o.findAllOrders();
    }
}
