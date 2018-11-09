package ua.nure.koshova.finalProject.servlet;

import ua.nure.koshova.finalProject.db.entity.Order;
import ua.nure.koshova.finalProject.service.OrderService;
import ua.nure.koshova.finalProject.servlet.constant.Pages;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/ordersList"})
public class ListOrdersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Order> allOrders = orderService.getOrderList();
        request.setAttribute("orders", allOrders);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher(Pages.LIST_ORDER_PAGE);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
