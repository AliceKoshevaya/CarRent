package ua.nure.koshova.finalProject.servlet;

import ua.nure.koshova.finalProject.service.CarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteCar")
public class DeleteCarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CarService carService = new CarService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("carId");
        Long idCar = Long.valueOf(id);
        carService.geleteCar(idCar);
        response.sendRedirect("/carList");
    }
}
