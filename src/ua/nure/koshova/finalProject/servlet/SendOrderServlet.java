package ua.nure.koshova.finalProject.servlet;

import ua.nure.koshova.finalProject.db.dao.BillDao;
import ua.nure.koshova.finalProject.db.dao.CarsDao;
import ua.nure.koshova.finalProject.db.entity.Bill;
import ua.nure.koshova.finalProject.db.entity.Car;
import ua.nure.koshova.finalProject.db.entity.Order;
import ua.nure.koshova.finalProject.service.BillService;
import ua.nure.koshova.finalProject.service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/finalOrder"})
public class SendOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderService orderService = new OrderService();
    private BillService billService = new BillService();
    private CarsDao carsDao = CarsDao.getInstance();

    public SendOrderServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idcar = request.getParameter("carId");
        Long idCar = Long.valueOf(idcar);
        String driver = request.getParameter("driver");
        String startRent = request.getParameter("startRent");
        String endRent = request.getParameter("endRent");
        String userName = request.getParameter("login");
        Long userId = Long.valueOf(userName);
        Long idOrder = orderService.newOrder(driver,startRent,endRent,userId,idCar);


        Car car = carsDao.findCarById(idCar);
        int priceCar = car.getPrice();
        Bill bill = billService.createBill(startRent, endRent, priceCar, idOrder);
//        request.setAttribute("bill", bill);
//        String pay = request.getParameter("pay");
//        if (pay == null) {
//
//        } else if (pay.equals("Pay")) {
//            bill.setStatus(true);
//            request.setAttribute("bill", bill);
//        }
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/BillView.jsp");

        dispatcher.forward(request, response);
    }
}

