package ua.nure.koshova.finalProject.service.Impl;

import ua.nure.koshova.finalProject.db.dao.OrderDao;
import ua.nure.koshova.finalProject.db.dao.impl.OrderDaoImpl;
import ua.nure.koshova.finalProject.db.entity.*;
import ua.nure.koshova.finalProject.service.OrderService;

import java.sql.Timestamp;
import java.util.List;

public class OrderServiceImpl implements OrderService{

    private static final boolean BILL_DEFAULT_PAYMENT_STATUS = false;
    private static final String BILL_DEFAULT_TYPE = "RENT";

    private OrderDao ordersDao = OrderDaoImpl.getInstance();

    public Bill newOrderAndBill(String driver, String startRent, String endRent, Long idUser, Car car) {
        Bill bill = new Bill();
        bill.setStatus(BILL_DEFAULT_PAYMENT_STATUS);
        bill.setType(BILL_DEFAULT_TYPE);

        Order order = new Order();
        bill.setOrder(order);

        order.setCar(car);
        order.setDriver(Boolean.getBoolean(driver));
        order.setStatus(OrderStatus.NEW);

        String startDate = startRent.replace("T", " ") + ":00";
        String endDate = endRent.replace("T", " ") + ":00";
        order.setStartRent(Timestamp.valueOf(startDate));
        order.setEndRent(Timestamp.valueOf(endDate));

        User user = new User();
        user.setId(idUser);
        order.setUser(user);

        int totalSum = calculateTotalSum(car, startDate, endDate);
        bill.setSum(totalSum);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        bill.setDate(timestamp);

        return ordersDao.createOrderAndRentBill(bill);
    }

    private int calculateTotalSum(Car car, String startDate, String endDate) {
        int time = (int)( (Timestamp.valueOf(endDate).getTime() - Timestamp.valueOf(startDate).getTime()) / 3600000 );
        return time*car.getPrice();
    }

    public List<Order> getOrderList() {
        return ordersDao.findAllOrders();
    }

    public void confirmOrder(Long id) {
        ordersDao.updateConfirmOrder(id);
    }

    public void crashOrder(Long id) {
        ordersDao.updateCrashOrder(id);
    }

    public void closeOrder(Long id) {
        ordersDao.updateCloseOrder(id);
    }

    public void updateReason(Long id, String name){
        ordersDao.updateReasonOrder(id,name);
    }

    public List<Order> findAllOrdersByUser(Long id){
        return ordersDao.findOrderByIdUser(id);
    }

}
