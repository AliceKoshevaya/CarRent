package ua.nure.koshova.finalProject.servlet;

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

    private CarService carService = new CarService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String carName = request.getParameter("CarName");
        String price = request.getParameter("Price");
        String stateNumber = request.getParameter("StateNumber");
        String brand = request.getParameter("Brand");
        String classCar = request.getParameter("Class");
        Long idBrand = Long.valueOf(brand);
        Long idClass = Long.valueOf(classCar);
        Integer priceCar = Integer.valueOf(price);
        carService.addNewCar(carName, priceCar, stateNumber, idBrand, idClass);
        response.sendRedirect("/carList");
    }

}