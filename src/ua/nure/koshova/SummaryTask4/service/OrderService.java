package ua.nure.koshova.SummaryTask4.service;

import ua.nure.koshova.SummaryTask4.db.entity.Bill;
import ua.nure.koshova.SummaryTask4.db.entity.Car;
import ua.nure.koshova.SummaryTask4.db.entity.Order;
import java.util.List;

/**
 * Interface for common order actions
 */
public interface OrderService {

    /**
     * Create a new order
     *
     * @param driver
     * @param startRent
     * @param endRent
     * @param idUser
     * @param car
     * @return new bill with order
     */
    Bill newOrderAndBill(String driver, String startRent, String endRent, Long idUser, Car car);


    /**
     * Get all orders
     *
     * @return {@Link List<Order>}
     */
    List<Order> getOrderList();

    /**
     * Change order status to confirmed
     *
     * @param id
     */

    void confirmOrder(Long id);

    /**
     * Change order status to crashed
     *
     * @param id
     */

    void crashOrder(Long id);

    /**
     * Change order status to closed
     *
     * @param id
     */

    void closeOrder(Long id);

    /**
     * Update reason to reject order
     *
     * @param id
     * @param name
     */

    void updateReason(Long id, String name);

    /**
     * Select all users order
     *
     * @param id
     * @return {@Link List<Order>}
     */
    List<Order> findAllOrdersByUser(Long id);

}
