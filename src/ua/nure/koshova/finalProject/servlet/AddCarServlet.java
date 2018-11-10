package ua.nure.koshova.finalProject.servlet;

import org.apache.log4j.Logger;
import ua.nure.koshova.finalProject.service.BrandService;
import ua.nure.koshova.finalProject.service.CarService;
import ua.nure.koshova.finalProject.service.ClassService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addCar")
public class AddCarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(AddCarServlet.class);

    private CarService carService = new CarService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String carName = request.getParameter("CarName");
        LOGGER.debug("Got carName parameter as " + carName);
        String price = request.getParameter("Price");
        LOGGER.debug("Got price for a car parameter as " + price);
        String stateNumber = request.getParameter("StateNumber");
        LOGGER.debug("Got a car stateNumber parameter as " + stateNumber);
        String brand = request.getParameter("Brand");
        LOGGER.debug("Got a car brand parameter as " + brand);
        String classCar = request.getParameter("Class");
        LOGGER.debug("Got a car class parameter as " + classCar);
        Long idBrand = Long.valueOf(brand);
        Long idClass = Long.valueOf(classCar);
        Integer priceCar = Integer.valueOf(price);
        carService.addNewCar(carName, priceCar, stateNumber, idBrand, idClass);
        response.sendRedirect("/carList");
    }

}