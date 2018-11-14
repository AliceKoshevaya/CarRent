package ua.nure.koshova.finalProject.service.Impl;

import ua.nure.koshova.finalProject.db.dao.impl.BillDaoImpl;
import ua.nure.koshova.finalProject.db.entity.Bill;
import ua.nure.koshova.finalProject.service.BillService;

import java.util.List;

import java.sql.Timestamp;

public class BillServiceImpl implements BillService{

    private BillDaoImpl billDaoImpl = BillDaoImpl.getInstance();

    public Bill createBill(String startRent, String endRent, int price, Long OrderId){
        String start = startRent.replace("T", " ") + ":00";
        String end = endRent.replace("T", " ") + ":00";
        int time = (int)( (Timestamp.valueOf(end).getTime() - Timestamp.valueOf(start).getTime())
                / 3600000 );
        int totalSum = time *price;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Long idBill = billDaoImpl.createBill("RENT", Boolean.FALSE, totalSum, timestamp, OrderId);
        return billDaoImpl.findBillById(idBill);
    }

    public Long createCrashBill(int sum, String date, Long orderId){
        return billDaoImpl.createBill("CRASH", Boolean.FALSE, sum, null, orderId );
    }

    public List<Bill> getAllBillByOrder(Long id){
        return billDaoImpl.findBillByIdOrder(id);
    }

}
