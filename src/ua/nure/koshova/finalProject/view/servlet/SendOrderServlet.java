package ua.nure.koshova.finalProject.view.servlet;

import org.apache.log4j.Logger;
import ua.nure.koshova.finalProject.db.entity.Bill;
import ua.nure.koshova.finalProject.db.entity.Car;
import ua.nure.koshova.finalProject.db.entity.User;
import ua.nure.koshova.finalProject.service.BillService;
import ua.nure.koshova.finalProject.service.CarService;
import ua.nure.koshova.finalProject.service.Impl.BillServiceImpl;
import ua.nure.koshova.finalProject.service.Impl.CarServiceImpl;
import ua.nure.koshova.finalProject.service.Impl.OrderServiceImpl;
import ua.nure.koshova.finalProject.service.Impl.UserServiceImpl;
import ua.nure.koshova.finalProject.service.OrderService;
import ua.nure.koshova.finalProject.service.UserService;
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
    private static final Logger LOGGER = Logger.getLogger(SendOrderServlet.class);

    private OrderService orderService = new OrderServiceImpl();
    private BillService billService = new BillServiceImpl();
    private CarService carService = new CarServiceImpl();
    private UserService userService = new UserServiceImpl();

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
        User user = userService.getUserByPassportSerial(series);

        String errorMessage = OrderValidator.validate(driver, startRent, endRent, thirdName, series, issued, user);
        if (!errorMessage.isEmpty()) {
            Car car = carService.getCarById(idCar);
            request.setAttribute("car", car);
            request.setAttribute("errorMessage", errorMessage);
            LOGGER.error("An error occurred while filling the order - " + errorMessage);
            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher(Pages.ORDER_PAGE);
            dispatcher.forward(request, response);
        } else {
            Long userId = Long.valueOf(userName);
            userService.addUserInfo(userId, thirdName, series, issued);
            Car car = carService.getCarById(idCar);
            Bill bill = orderService.newOrderAndBill(driver, startRent, endRent, userId, car);
            Long id = bill.getId();
            response.sendRedirect("/bill?idBill=" + id);
        }

    }
}

