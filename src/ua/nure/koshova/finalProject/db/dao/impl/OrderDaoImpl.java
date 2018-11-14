package ua.nure.koshova.finalProject.db.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.koshova.finalProject.db.dao.OrderDao;
import ua.nure.koshova.finalProject.db.dao.util.DatabaseRequests;
import ua.nure.koshova.finalProject.db.dao.util.DatabaseUtils;
import ua.nure.koshova.finalProject.db.entity.*;
import ua.nure.koshova.finalProject.db.exception.CloseResourcesException;
import ua.nure.koshova.finalProject.db.exception.QueryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);
    private static final String ERROR_MESSAGE_UPDATE_CLOSE_ORDER = "Can't update order to status closed by id (id = %d)";
    private static final String ERROR_MESSAGE_UPDATE_REASON_ORDER = "Can't update reason to reject in order by id (id = %d reason =%s)";
    private static final String ERROR_MESSAGE_UPDATE_CRASH_ORDER = "Can't update order to status crash by id (id = %d)";
    private static final String ERROR_MESSAGE_UPDATE_CONFIRM_ORDER = "Can't update order to status confirm by id (id = %d)";
    private static final String ERROR_MESSAGE_SELECT_ALL_ORDERS = "Can't select all orders";
    private static final String ERROR_MESSAGE_CREATE_ORDER = "Can't create a new order";
    private static final String ERROR_MESSAGE_SELECT_ORDER_BY_USER = "Can't select order by id (id = %d)";
    private static volatile OrderDaoImpl instance;

    private OrderDaoImpl() {

    }

    public static OrderDaoImpl getInstance() {
        OrderDaoImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (CarDaoImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new OrderDaoImpl();
                }
            }
        }
        return localInstance;
    }

    public Bill createOrderAndRentBill(Bill bill) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();

        ResultSet generatedKeys = null;

        if (bill != null && bill.getOrder() != null) {
            try (
                    PreparedStatement psOrder =
                            connection.prepareStatement(DatabaseRequests.INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement psBill =
                            connection.prepareStatement(DatabaseRequests.INSERT_BILL, Statement.RETURN_GENERATED_KEYS)
            ) {
                connection.setAutoCommit(false);
                Order order = bill.getOrder();

                psOrder.setBoolean(1, order.isDriver());
                psOrder.setString(2, order.getStatus().toString());
                psOrder.setTimestamp(3, order.getStartRent());
                psOrder.setTimestamp(4, order.getEndRent());
                psOrder.setLong(5, order.getUser().getId());
                psOrder.setLong(6, order.getCar().getId());
                psOrder.executeUpdate();

                generatedKeys = psOrder.getGeneratedKeys();

                if (null != generatedKeys && generatedKeys.next()) {
                    Long idOrder = generatedKeys.getLong(1);
                    order.setId(idOrder);

                    psBill.setBoolean(1, bill.getStatus());
                    psBill.setString(2, bill.getType());
                    psBill.setInt(3, bill.getSum());
                    psBill.setTimestamp(4, bill.getDate());
                    psBill.setLong(5, idOrder);

                    psBill.executeUpdate();

                    generatedKeys = psBill.getGeneratedKeys();

                    if (null != generatedKeys && generatedKeys.next()) {
                        Long idBill = generatedKeys.getLong(1);
                        bill.setId(idBill);
                    } else {
                        throw new SQLException("Bill was not created");
                    }
                } else {
                    throw new SQLException("Order was not created");
                }

            } catch (SQLException ex) {
                DatabaseUtils.rollback(connection);
                LOGGER.error(ERROR_MESSAGE_CREATE_ORDER, ex);
                throw new QueryException(ERROR_MESSAGE_CREATE_ORDER, ex);
            } finally {
                DatabaseUtils.commit(connection);
                DatabaseUtils.closeResultSet(generatedKeys);
                DatabaseUtils.closeConnection(connection);
            }
        }
        return bill;
    }

    public List<Order> findAllOrders() throws QueryException, CloseResourcesException {
        List<Order> orderList = new ArrayList<>();
        Connection connection = DatabaseUtils.getConnection();

        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_ALL_ORDERS)) {
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
            DatabaseUtils.closeConnection(connection);
        }
        return orderList;
    }

    public void updateConfirmOrder(Long id) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.CONFIRM_ORDER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_UPDATE_CONFIRM_ORDER, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_UPDATE_CONFIRM_ORDER, id), ex);
        } finally {
            DatabaseUtils.closeConnection(connection);
        }
    }

    public void updateCrashOrder(Long id) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.CRASH_ORDER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_UPDATE_CRASH_ORDER, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_UPDATE_CRASH_ORDER, id), ex);
        } finally {
            DatabaseUtils.closeConnection(connection);
        }
    }

    public void updateCloseOrder(Long id) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.CLOSE_ORDER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_UPDATE_CLOSE_ORDER, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_UPDATE_CLOSE_ORDER, id), ex);
        } finally {
            DatabaseUtils.closeConnection(connection);
        }
    }

    public void updateReasonOrder(Long id, String reason) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.UPDATE_REASON)) {
            preparedStatement.setString(1, reason);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_UPDATE_REASON_ORDER, id, reason), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_UPDATE_REASON_ORDER, id, reason), ex);
        } finally {
            DatabaseUtils.closeConnection(connection);
        }
    }

    public List<Order> findOrderByIdUser(Long id) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();

        List<Order> orderList = new ArrayList<>();

        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(DatabaseRequests.SELECT_ORDER_BY_ID_USER)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setDriver(resultSet.getBoolean(1));
                order.setStatus(OrderStatus.valueOf(resultSet.getString(2).toUpperCase()));
                order.setStartRent(resultSet.getTimestamp(3));
                order.setEndRent(resultSet.getTimestamp(4));
                orderList.add(order);
            }
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_SELECT_ORDER_BY_USER, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_SELECT_ORDER_BY_USER, id), ex);
        } finally {
            DatabaseUtils.closeResultSet(resultSet);
            DatabaseUtils.closeConnection(connection);
        }
        return orderList;
    }

}
