package ua.nure.koshova.finalProject.db.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.koshova.finalProject.db.dao.IBillDao;
import ua.nure.koshova.finalProject.db.dao.util.DatabaseRequests;
import ua.nure.koshova.finalProject.db.dao.util.DatabaseUtils;
import ua.nure.koshova.finalProject.db.entity.Bill;
import ua.nure.koshova.finalProject.db.entity.Order;
import ua.nure.koshova.finalProject.db.exception.CloseResourcesException;
import ua.nure.koshova.finalProject.db.exception.QueryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDao implements IBillDao {

    private static final Logger LOGGER = Logger.getLogger(BillDao.class);
    private static volatile BillDao instance;

    private static final String ERROR_MESSAGE_UPDATE_BILL = "Can't update user bill (id = %d)";
    private static final String ERROR_MESSAGE_SELECT_BILL_BY_ORDER = "Can't select user bill by order id (id = %d)";
    private static final String ERROR_MESSAGE_INSERT_BILL = "Unable to perform operation insert bill ";
    private static final String ERROR_MESSAGE_SELECT_BILL = "Can't select user bill (id = %d)";

    private BillDao() {
    }

    public static BillDao getInstance() {
        BillDao localInstance = instance;
        if (localInstance == null) {
            synchronized (BillDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new BillDao();
                }
            }
        }
        return localInstance;
    }

    public Long createBill(String type, Boolean status, int sum, Timestamp date, Long idOrder)
            throws QueryException, CloseResourcesException {

        Connection connection = DatabaseUtils.getConnection();

        Long id = null;
        ResultSet generatedKeys = null;

        try (PreparedStatement ps =
                     connection.prepareStatement(DatabaseRequests.INSERT_BILL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setBoolean(1, status);
            ps.setString(2, type);
            ps.setInt(3, sum);
            ps.setTimestamp(4, date);
            ps.setLong(5, idOrder);

            ps.executeUpdate();

            generatedKeys = ps.getGeneratedKeys();

            if (null != generatedKeys && generatedKeys.next()) {
                id = generatedKeys.getLong(1);
            }

        } catch (SQLException ex) {
            LOGGER.error(ERROR_MESSAGE_INSERT_BILL, ex);
            throw new QueryException(ERROR_MESSAGE_INSERT_BILL, ex);
        } finally {
            DatabaseUtils.closeResultSet(generatedKeys);
            DatabaseUtils.closeConnection(connection);
        }

        return id;
    }

    public Bill findBillById(Long id) throws QueryException, CloseResourcesException {

        Connection connection = DatabaseUtils.getConnection();

        Bill bill = null;
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(DatabaseRequests.SELECT_BILL_BY_ID)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bill = new Bill();
                Order order = new Order();
                bill.setId(resultSet.getLong(1));
                bill.setStatus(resultSet.getBoolean(2));
                bill.setType(resultSet.getString(3));
                bill.setSum(resultSet.getInt(4));
                bill.setDate(resultSet.getTimestamp(5));
                order.setId(resultSet.getLong(6));
                bill.setOrder(order);
            }
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_SELECT_BILL, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_SELECT_BILL, id), ex);
        } finally {
            DatabaseUtils.closeResultSet(resultSet);
            DatabaseUtils.closeConnection(connection);
        }

        return bill;
    }

    public List<Bill> findBillByIdOrder(Long id) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();
        List<Bill> billList = new ArrayList<>();

        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(DatabaseRequests.SELECT_BILL_BY_ORDER_ID)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bill bill = new Bill();
                bill.setId(resultSet.getLong(1));
                bill.setStatus(resultSet.getBoolean(2));
                bill.setType(resultSet.getString(3));
                bill.setSum(resultSet.getInt(4));
                bill.setDate(resultSet.getTimestamp(5));
                billList.add(bill);
            }
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_SELECT_BILL_BY_ORDER, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_SELECT_BILL_BY_ORDER, id), ex);
        } finally {
            DatabaseUtils.closeResultSet(resultSet);
            DatabaseUtils.closeConnection(connection);
        }
        return billList;
    }

    public void updateBill(Long id) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(DatabaseRequests.UPDATE_BILL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_UPDATE_BILL, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_UPDATE_BILL, id), ex);
        } finally {
            DatabaseUtils.closeConnection(connection);
        }
    }

}
