package ua.nure.koshova.finalProject.servlet;

import ua.nure.koshova.finalProject.entity.Car;
import ua.nure.koshova.finalProject.service.CarService;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = { "/carList" })
public class CarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CarService carService = new CarService();

    public CarServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Car> allCar = carService.findAllCar();
        request.setAttribute("cars", allCar);
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/CarList.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}