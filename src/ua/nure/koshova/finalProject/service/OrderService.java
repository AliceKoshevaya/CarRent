package ua.nure.koshova.finalProject.service;


import ua.nure.koshova.finalProject.db.dao.OrdersDao;
import ua.nure.koshova.finalProject.db.entity.Car;
import ua.nure.koshova.finalProject.db.entity.Order;
import ua.nure.koshova.finalProject.db.entity.OrderStatus;
import ua.nure.koshova.finalProject.db.entity.User;

import java.sql.Timestamp;
import java.util.List;

public class OrderService {

    private OrdersDao ordersDao = OrdersDao.getInstance();

    public static void main(String[] args) {
        OrderService os = new OrderService();
        os.newOrder("true", "2018-11-02 13:00", "2018-11-02 14:00", 1L, 2l);
    }

    public Long newOrder(String driver, String startRent, String endRent, Long idUser, Long idCar) {
        Order order = new Order();
        String start = startRent.replace("T", " ") + ":00";
        String end = endRent.replace("T", " ") + ":00";
        order.setDriver(Boolean.getBoolean(driver));
        order.setStartRent(Timestamp.valueOf(start));
        order.setEndRent(Timestamp.valueOf(end));
        Car car = new Car();
        car.setId(idCar);
        User user = new User();
        user.setId(idUser);
        order.setUser(user);
        order.setCar(car);
        order.setStatus(OrderStatus.NEW);
        Long id = ordersDao.createOrder(order);
        return id;
    }

    public List<Order> getOrderList() {
        List<Order> orderList = ordersDao.findAllOrders();
        return orderList;
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
}
