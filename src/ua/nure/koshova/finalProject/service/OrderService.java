package ua.nure.koshova.finalProject.service;

import ua.nure.koshova.finalProject.db.dao.BillDao;
import ua.nure.koshova.finalProject.db.dao.CarsDao;
import ua.nure.koshova.finalProject.db.dao.OrdersDao;
import ua.nure.koshova.finalProject.db.dao.UsersDao;
import ua.nure.koshova.finalProject.db.entity.Bill;
import ua.nure.koshova.finalProject.db.entity.Car;
import ua.nure.koshova.finalProject.db.entity.Order;
import ua.nure.koshova.finalProject.db.entity.User;

import java.sql.Date;
import java.sql.Timestamp;

public class OrderService {

    private OrdersDao ordersDao = OrdersDao.getInstance();

    public Long newOrder(String driver, String startRent, String endRent,  Long idUser, Long idCar){
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
        Long id = ordersDao.createOrder(order);
        return id;
    }

    public static void main(String[] args) {
    }
}
