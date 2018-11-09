package ua.nure.koshova.finalProject.servlet;

import ua.nure.koshova.finalProject.db.entity.Car;
import ua.nure.koshova.finalProject.service.CarService;
import ua.nure.koshova.finalProject.servlet.constant.Pages;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/makeOrder"})
public class OrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CarService carService = new CarService();

    public OrderServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stringIdCar = request.getParameter("carId");
        Long idCar = Long.valueOf(stringIdCar);
        Car car = carService.getCarById(idCar);
        request.setAttribute("car", car);
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Pages.ORDER_PAGE);
        dispatcher.forward(request, response);
    }
}
