package ua.nure.koshova.finalProject.service;

import ua.nure.koshova.finalProject.db.entity.Order;
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
     * @param idCar
     * @return id order
     */
    public Long newOrder(String driver, String startRent, String endRent, Long idUser, Long idCar);


    /**
     * Get all orders
     *
     * @return {@Link List<Order>}
     */
    public List<Order> getOrderList();

    /**
     * Change order status to confirmed
     *
     * @param id
     */

    public void confirmOrder(Long id);

    /**
     * Change order status to crashed
     *
     * @param id
     */

    public void crashOrder(Long id);

    /**
     * Change order status to closed
     *
     * @param id
     */

    public void closeOrder(Long id);

    /**
     * Update reason to reject order
     *
     * @param id
     * @param name
     */

    public void updateReason(Long id, String name);

    /**
     * Select all users order
     *
     * @param id
     * @return {@Link List<Order>}
     */

    public List<Order> findAllOrdersByUser(Long id);

}
