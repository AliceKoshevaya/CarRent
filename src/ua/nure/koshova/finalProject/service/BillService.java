package ua.nure.koshova.finalProject.service;

import ua.nure.koshova.finalProject.db.dao.impl.BillDao;
import ua.nure.koshova.finalProject.db.entity.Bill;

import java.util.List;

import java.sql.Timestamp;

public class BillService {

    private BillDao billDao = BillDao.getInstance();

    public Bill createBill(String startRent, String endRent, int price, Long OrderId){
        String start = startRent.replace("T", " ") + ":00";
        String end = endRent.replace("T", " ") + ":00";
        int time = (int)( (Timestamp.valueOf(end).getTime() - Timestamp.valueOf(start).getTime())
                / 3600000 );
        int totalSum = time *price;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Long idBill = billDao.createBill("RENT", Boolean.FALSE, totalSum, timestamp, OrderId);
        return billDao.findBillById(idBill);
    }

    public Long createCrashBill(int sum, String date, Long orderId){
        return billDao.createBill("CRASH", Boolean.FALSE, sum, null, orderId );
    }

    public List<Bill> getAllBillByOrder(Long id){
        return billDao.findBillByIdOrder(id);
    }

}
