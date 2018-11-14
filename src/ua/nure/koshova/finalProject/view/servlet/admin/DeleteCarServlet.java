package ua.nure.koshova.finalProject.view.servlet.admin;

import ua.nure.koshova.finalProject.service.Impl.CarServiceImpl;
import ua.nure.koshova.finalProject.view.constant.Pages;
import ua.nure.koshova.finalProject.view.util.right.RightChecker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteCar")
public class DeleteCarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CarServiceImpl carServiceImpl = new CarServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!RightChecker.isAdmin(request.getSession())) {
            response.sendRedirect(Pages.ERROR_PAGE_NOT_ENOUGH_RIGTH);
            return;
        }
        String stringIdCar = request.getParameter("carId");
        Long idCar = Long.valueOf(stringIdCar);
        carServiceImpl.deleteCar(idCar);
        response.sendRedirect("/carList");
    }
}
