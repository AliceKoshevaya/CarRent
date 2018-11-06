package ua.nure.koshova.finalProject.servlet;

import ua.nure.koshova.finalProject.db.entity.Car;
import ua.nure.koshova.finalProject.db.entity.Status;
import ua.nure.koshova.finalProject.service.CarService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editCar")
public class EditACarServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    private CarService carService = new CarService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("carId");
        Long idCar = Long.valueOf(id);
        Car car = carService.getCarById(idCar);
        request.setAttribute("car", car);
        request.setAttribute("statuses", Status.values());
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/EditCarView.jsp");

        dispatcher.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/EditCarView.jsp");

        dispatcher.forward(request, response);

    }
}
