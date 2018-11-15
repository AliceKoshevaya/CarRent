package ua.nure.koshova.SummaryTask4.view.servlet.manager;

import ua.nure.koshova.SummaryTask4.db.entity.Order;
import ua.nure.koshova.SummaryTask4.service.Impl.OrderServiceImpl;
import ua.nure.koshova.SummaryTask4.view.constant.Pages;
import ua.nure.koshova.SummaryTask4.view.util.right.RightChecker;

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

    private OrderServiceImpl orderServiceImpl = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!RightChecker.isManager(request.getSession())) {
            response.sendRedirect(Pages.ERROR_PAGE_NOT_ENOUGH_RIGTH);
            return;
        }
        List<Order> allOrders = orderServiceImpl.getOrderList();
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
