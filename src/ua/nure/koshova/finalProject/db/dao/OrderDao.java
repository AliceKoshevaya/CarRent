package ua.nure.koshova.finalProject.db.dao;


import ua.nure.koshova.finalProject.db.entity.Order;

import java.util.List;

public interface OrderDao {

    /**
     * Create a new order
     *
     * @param {@link Order}
     * @return id order
     */
    Long createOrder(Order order);

    /**
     * Select all orders
     *
     * @return {@link List<Order>}
     */

    List<Order> findAllOrders();

    /**
     * Update status on confirm at order
     *
     * @param id
     */

    void updateConfirmOrder(Long id);

    /**
     * Update status on crash at order
     *
     * @param id
     */

    void updateCrashOrder(Long id);

    /**
     * Update status on close at order
     *
     * @param id
     */

    void updateCloseOrder(Long id);

    /**
     * Update reason for reject order by manager
     *
     * @param id
     * @param reason
     */

    void updateReasonOrder(Long id, String reason);
}
