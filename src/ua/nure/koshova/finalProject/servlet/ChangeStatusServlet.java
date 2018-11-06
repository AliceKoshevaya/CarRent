package ua.nure.koshova.finalProject.servlet;

import ua.nure.koshova.finalProject.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/confirm")
public class ChangeStatusServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderService orderService = new OrderService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("idOrder");
        String crash = request.getParameter("crash");
        Long idOrder = Long.valueOf(id);
        String status = request.getParameter("status");
        if (status.equals("NEW")) {
            orderService.confirmOrder(idOrder);
        }
        else if(status.equals("CRASH") || crash.equals("Crash")) {
            orderService.crashOrder(idOrder);
        }
        else if(status.equals("IN_PROGRESS")){
            orderService.closeOrder(idOrder);
        }
        response.sendRedirect("/ordersList");

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
