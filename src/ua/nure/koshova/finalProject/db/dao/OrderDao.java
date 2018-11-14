package ua.nure.koshova.finalProject.db.dao;


import ua.nure.koshova.finalProject.db.entity.Bill;
import ua.nure.koshova.finalProject.db.entity.Order;
import ua.nure.koshova.finalProject.db.exception.CloseResourcesException;
import ua.nure.koshova.finalProject.db.exception.QueryException;

import java.util.List;

public interface OrderDao {

    /**
     * Create a new order
     *
     * @param {@link Order}
     * @return id order
     */
    Bill createOrderAndRentBill(Bill bill);

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

    /**
     * All orders by user id
     *
     * @param id
     * @return
     */
    List<Order> findOrderByIdUser(Long id);
}
