package ua.nure.koshova.finalProject.servlet;

import ua.nure.koshova.finalProject.db.dao.CarDao;
import ua.nure.koshova.finalProject.db.entity.Bill;
import ua.nure.koshova.finalProject.db.entity.Car;
import ua.nure.koshova.finalProject.service.BillService;
import ua.nure.koshova.finalProject.service.OrderService;
import ua.nure.koshova.finalProject.service.UserService;

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
    private CarDao carsDao = CarDao.getInstance();
    private UserService userService = new UserService();

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
        String thirdName = request.getParameter("thirdName");
        String seria = request.getParameter("seria");
        String issued = request.getParameter("issued");

        Long userId = Long.valueOf(userName);
        Long idOrder = orderService.newOrder(driver,startRent,endRent,userId,idCar);

        userService.addUserInfo(userId,thirdName,seria,issued);

        Car car = carsDao.findCarById(idCar);
        int priceCar = car.getPrice();
        Bill bill = billService.createBill(startRent, endRent, priceCar, idOrder);
        Long id = bill.getId();
        response.sendRedirect("/bill?idBill=" + id);
    }
}

