package ua.nure.koshova.finalProject.service;

import ua.nure.koshova.finalProject.db.entity.Bill;

import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for common bill actions
 */
public interface BillService {
    /**
     * Create a new bill for an order
     *
     * @param startRent
     * @param endRent
     * @param price
     * @param OrderId
     * @return {@link Bill}
     */
    Bill createBill(String startRent, String endRent, int price, Long OrderId);

    /**
     * Creating a new bill for crash
     *
     * @param sum
     * @param date
     * @param orderId
     * @return id bill
     */

    Long createCrashBill(int sum, Timestamp date, Long orderId);

    /**
     * Receipt bill by id
     *
     * @param id
     * @return {@link List<Bill>}
     */
    List<Bill> getAllBillByOrder(Long id);

}
