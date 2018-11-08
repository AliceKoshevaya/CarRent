package ua.nure.koshova.finalProject.db.dao;

import ua.nure.koshova.finalProject.db.dao.util.MySQLConnUtils;
import ua.nure.koshova.finalProject.db.dao.util.RequestsToDB;
import ua.nure.koshova.finalProject.db.entity.Bill;
import ua.nure.koshova.finalProject.db.entity.Order;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;

public class BillDao {
    private static volatile BillDao instance;

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

    public Long createBill(String type, Boolean status, int sum, Timestamp date, Long idOrder) {
        Long id = null;
        Connection con = MySQLConnUtils.getMySQLConnection();

        try (PreparedStatement ps = con.prepareStatement(RequestsToDB.INSERT_BILL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setBoolean(1, status);
            ps.setString(2,type);
            ps.setInt(3, sum);
            ps.setTimestamp(4, date);
            ps.setLong(5, idOrder);

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (null != generatedKeys && generatedKeys.next()) {
                id = generatedKeys.getLong(1);
            }

        } catch (SQLException s) {
            s.printStackTrace();
        }

        return id;
    }

    public Bill findBillById(Long id) {
        Connection connection = MySQLConnUtils.getMySQLConnection();
        Bill bill = new Bill();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(RequestsToDB.SELECT_BILL_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                bill.setOrder(order);
                bill.setId(resultSet.getLong(1));
                bill.setStatus(resultSet.getBoolean(2));
                bill.setType(resultSet.getString(3));
                bill.setSum(resultSet.getInt(4));
                bill.setDate(resultSet.getTimestamp(5));
                order.setId(resultSet.getLong(6));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return bill;
    }

    public List<Bill> findBillByIdOrder(Long id) {
        Connection connection = MySQLConnUtils.getMySQLConnection();
        List<Bill> billList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(RequestsToDB.SELECT_BILL_BY_ORDER_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bill bill = new Bill();
                bill.setId(resultSet.getLong(1));
                bill.setStatus(resultSet.getBoolean(2));
                bill.setType(resultSet.getString(3));
                bill.setSum(resultSet.getInt(4));
                bill.setDate(resultSet.getTimestamp(5));
                billList.add(bill);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return billList;
    }

    public void updateBill(Long id) {
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.UPDATE_BILL);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
