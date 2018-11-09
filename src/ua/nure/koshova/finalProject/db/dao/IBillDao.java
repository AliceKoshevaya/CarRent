package ua.nure.koshova.finalProject.db.dao;

import ua.nure.koshova.finalProject.db.entity.Bill;

import java.sql.*;
import java.util.List;

/**
 * DAO interface for bill entity
 */
public interface IBillDao {

    /**
     * Create new bill for user
     *
     * @param  type
     * @param  status
     * @param  sum
     * @param  date
     * @param  idOrder
     *
     * @return bill id
     *
     *
     */
    Long createBill(String type, Boolean status, int sum, Timestamp date, Long idOrder);

    /**
     * Find bill by id
     *
     * @param id
     *
     * @return  {@link Bill}
     */
    Bill findBillById(Long id);

    /**
     * Find bill by order id
     *
     * @param id
     *
     * @return {@link List<Bill>}
     */

    List<Bill> findBillByIdOrder(Long id);

    /**
     * Update bill
     *
     * @param id
     */
    void updateBill(Long id);
}
