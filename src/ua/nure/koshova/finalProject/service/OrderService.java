package ua.nure.koshova.finalProject.service;


import ua.nure.koshova.finalProject.db.dao.impl.OrderDao;
import ua.nure.koshova.finalProject.db.dao.util.DatabaseUtils;
import ua.nure.koshova.finalProject.db.entity.Car;
import ua.nure.koshova.finalProject.db.entity.Order;
import ua.nure.koshova.finalProject.db.entity.OrderStatus;
import ua.nure.koshova.finalProject.db.entity.User;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class OrderService {

    private OrderDao ordersDao = OrderDao.getInstance();

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
