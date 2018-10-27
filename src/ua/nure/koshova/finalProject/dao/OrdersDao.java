package ua.nure.koshova.finalProject.dao;

import ua.nure.koshova.finalProject.entity.*;

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

    public List<Order> findAllOrders() throws SQLException {
        List<Order> orderList = new ArrayList<Order>();
        Connection connection = MySQLConnUtils.getMySQLConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select o.id, o.driver, o.start_rent, o.end_rent,  u.id, u.user_login, b.type, c.id, c.name \n" +
                "from orders AS o left join users AS u ON o.id_user = u.id left join bill  AS b ON o.id_bill= b.id\n" +
                "left join cars AS c ON o.id_car = c.id order by o.id;");
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
        return orderList;
    }

    public static void main(String[] args) throws SQLException {
        OrdersDao o = new OrdersDao();
        o.findAllOrders();
    }
}
