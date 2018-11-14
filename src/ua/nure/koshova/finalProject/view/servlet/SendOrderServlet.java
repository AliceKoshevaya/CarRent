package ua.nure.koshova.finalProject.view.servlet;

import org.apache.log4j.Logger;
import ua.nure.koshova.finalProject.db.dao.impl.CarDaoImpl;
import ua.nure.koshova.finalProject.db.entity.Bill;
import ua.nure.koshova.finalProject.db.entity.Car;
import ua.nure.koshova.finalProject.service.Impl.BillServiceImpl;
import ua.nure.koshova.finalProject.service.Impl.OrderServiceImpl;
import ua.nure.koshova.finalProject.service.Impl.UserServiceImpl;
import ua.nure.koshova.finalProject.view.constant.Pages;
import ua.nure.koshova.finalProject.view.util.validator.OrderValidator;

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

    private OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
    private BillServiceImpl billServiceImpl = new BillServiceImpl();
    private CarDaoImpl carsDao = CarDaoImpl.getInstance();
    private UserServiceImpl userServiceImpl = new UserServiceImpl();

    private static final Logger LOGGER = Logger.getLogger(SendOrderServlet.class);

    public SendOrderServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stringIdCar = request.getParameter("carId");
        LOGGER.debug("Got idCar parameter as " + stringIdCar);
        Long idCar = Long.valueOf(stringIdCar);
        String driver = request.getParameter("driver");
        LOGGER.debug("Got driver parameter as " + driver);
        String startRent = request.getParameter("startRent");
        LOGGER.debug("Got startRent parameter as " + startRent);
        String endRent = request.getParameter("endRent");
        LOGGER.debug("Got endRent parameter as " + endRent);
        String userName = request.getParameter("login");
        LOGGER.debug("Got userName parameter as " + userName);
        String thirdName = request.getParameter("thirdName");
        LOGGER.debug("Got thirdName parameter as " + thirdName);
        String series = request.getParameter("seria");
        LOGGER.debug("Got passport series parameter as " + series);
        String issued = request.getParameter("issued");
        LOGGER.debug("Got issued parameter as " + issued);
        String errorMessage = OrderValidator.validate(driver, startRent, endRent, thirdName, series, issued);
        if (!errorMessage.isEmpty()) {
            request.setAttribute("errorMessage", errorMessage);
            LOGGER.error("An error occurred while filling the order - " + errorMessage);
            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher(Pages.ORDER_PAGE);
            dispatcher.forward(request, response);
        } else {

            Long userId = Long.valueOf(userName);
            Long idOrder = orderServiceImpl.newOrder(driver, startRent, endRent, userId, idCar);

            if (!userServiceImpl.passportSeriaExist()) {
                errorMessage = "This passport series already exists.";
                request.setAttribute("errorMessage", errorMessage);
                LOGGER.error("An error occurred while filling the field passport series - " + errorMessage);
                RequestDispatcher dispatcher
                        = this.getServletContext().getRequestDispatcher(Pages.ORDER_PAGE);
                dispatcher.forward(request, response);
            }else {
                userServiceImpl.addUserInfo(userId, thirdName, series, issued);
                Car car = carsDao.findCarById(idCar);
                int priceCar = car.getPrice();
                Bill bill = billServiceImpl.createBill(startRent, endRent, priceCar, idOrder);
                Long id = bill.getId();
                response.sendRedirect("/bill?idBill=" + id);
            }
        }
    }
}

