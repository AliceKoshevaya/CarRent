package ua.nure.koshova.SummaryTask4.service.Impl;

import ua.nure.koshova.SummaryTask4.db.dao.BillDao;
import ua.nure.koshova.SummaryTask4.db.dao.impl.BillDaoImpl;
import ua.nure.koshova.SummaryTask4.db.entity.Bill;
import ua.nure.koshova.SummaryTask4.service.BillService;

import java.util.List;

import java.sql.Timestamp;

public class BillServiceImpl implements BillService{

    private BillDao billDao = BillDaoImpl.getInstance();

    public Bill createBill(String startRent, String endRent, int price, Long OrderId){
        String start = startRent.replace("T", " ") + ":00";
        String end = endRent.replace("T", " ") + ":00";
        int time = (int)( (Timestamp.valueOf(end).getTime() - Timestamp.valueOf(start).getTime())
                / 3600000 );
        int totalSum = time*price;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Long idBill = billDao.createBill("RENT", Boolean.FALSE, totalSum, timestamp, OrderId);
        return billDao.findBillById(idBill);
    }

    public Long createCrashBill(int sum, Timestamp date, Long orderId){
        return billDao.createBill("CRASH", Boolean.FALSE, sum, date, orderId);
    }

    public List<Bill> getAllBillByOrder(Long id){
        return billDao.findBillByIdOrder(id);
    }

}
