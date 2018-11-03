package ua.nure.koshova.finalProject.service;

import ua.nure.koshova.finalProject.db.dao.BillDao;
import ua.nure.koshova.finalProject.db.entity.Bill;

import java.sql.Timestamp;

public class BillService {

    private BillDao billDao = BillDao.getInstance();

    public Bill createBill(String startRent, String endRent, int price, Long OrderId){
        String start = startRent.replace("T", " ") + ":00";
        String end = endRent.replace("T", " ") + ":00";
        int time = (int)( (Timestamp.valueOf(end).getTime() - Timestamp.valueOf(start).getTime())
                / 3600000 );
        int totalSum = time *price;
        Long idBill = billDao.createBill("RENT", Boolean.FALSE, totalSum, null, OrderId);
        Bill bill = billDao.findBillById(idBill);
        return bill;
    }
}
