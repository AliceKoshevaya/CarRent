package ua.nure.koshova.finalProject.db.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.koshova.finalProject.db.dao.IOrderDao;
import ua.nure.koshova.finalProject.db.dao.util.DatabaseUtils;
import ua.nure.koshova.finalProject.db.dao.util.DatabaseRequests;
import ua.nure.koshova.finalProject.db.entity.Car;
import ua.nure.koshova.finalProject.db.entity.Order;
import ua.nure.koshova.finalProject.db.entity.OrderStatus;
import ua.nure.koshova.finalProject.db.entity.User;
import ua.nure.koshova.finalProject.db.exception.CloseResourcesException;
import ua.nure.koshova.finalProject.db.exception.QueryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IOrderDao {

    private static final Logger LOGGER = Logger.getLogger(OrderDao.class);
    private static volatile OrderDao instance;

    public static final String ERROR_MESSAGE_SELECT_ALL_ORDERS= "Can't select all orders";
    public static final String ERROR_MESSAGE_CREATE_ORDER = "Can't create a new order";

    private OrderDao() {

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

    public Long createOrder(Order order) throws QueryException, CloseResourcesException {
        Long id = null;
        Connection con = DatabaseUtils.getConnection();

        ResultSet generatedKeys = null;

        if (order != null) {
            try (PreparedStatement ps = con.prepareStatement(DatabaseRequests.INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
                ps.setBoolean(1, order.isDriver());
                ps.setString(2, order.getStatus().toString());
                ps.setTimestamp(3, order.getStartRent());
                ps.setTimestamp(4, order.getEndRent());
                ps.setLong(5, order.getUser().getId());
                ps.setLong(6, order.getCar().getId());
                ps.executeUpdate();

                generatedKeys = ps.getGeneratedKeys();

                if (null != generatedKeys && generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                }

            } catch (SQLException ex) {
                LOGGER.error(ERROR_MESSAGE_CREATE_ORDER, ex);
                throw new QueryException(ERROR_MESSAGE_CREATE_ORDER, ex);
            } finally {
                DatabaseUtils.closeResultSet(generatedKeys);
            }
        }
        return id;
    }

    public List<Order> findAllOrders() throws QueryException, CloseResourcesException {
        List<Order> orderList = new ArrayList<>();
        Connection connection = DatabaseUtils.getConnection();

        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_ALL_ORDERS);
            resultSet = preparedStatement.executeQuery();
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
        } catch (SQLException ex) {
            LOGGER.error(ERROR_MESSAGE_SELECT_ALL_ORDERS, ex);
            throw new QueryException(ERROR_MESSAGE_SELECT_ALL_ORDERS, ex);
        } finally {
            DatabaseUtils.closeResultSet(resultSet);
        }
        return orderList;
    }

    public void updateConfirmOrder(Long id) throws QueryException, CloseResourcesException {
        Connection con = DatabaseUtils.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(DatabaseRequests.CONFIRM_ORDER);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error("Can't update order to status confirm by id (id = " + id + ")", ex);
            throw new QueryException("Can't update order to status confirm by id (id = " + id + ")", ex);
        }
    }

    public void updateCrashOrder(Long id) throws QueryException, CloseResourcesException {
        Connection con = DatabaseUtils.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(DatabaseRequests.CRASH_ORDER);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error("Can't update order to status crash by id (id = " + id + ")", ex);
            throw new QueryException("Can't update order to status crash by id (id = " + id + ")", ex);
        }
    }

    public void updateCloseOrder(Long id) throws QueryException, CloseResourcesException {
        Connection con = DatabaseUtils.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(DatabaseRequests.CLOSE_ORDER);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error("Can't update order to status closed by id (id = " + id + ")", ex);
            throw new QueryException("Can't update order to status closed by id (id = " + id + ")", ex);
        }
    }

    public void updateReasonOrder(Long id, String reason) throws QueryException, CloseResourcesException {
        Connection con = DatabaseUtils.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(DatabaseRequests.UPDATE_REASON);
            preparedStatement.setString(1, reason);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error("Can't update reason to reject in order by id (id = " + id + " reason =" + reason + ")", ex);
            throw new QueryException("Can't update reason to reject in order by id (id = " + id + " reason =" + reason + ")", ex);
        }
    }
}
