package ua.nure.koshova.finalProject.view.servlet.manager;

import ua.nure.koshova.finalProject.db.entity.OrderStatus;
import ua.nure.koshova.finalProject.service.OrderService;
import ua.nure.koshova.finalProject.view.constant.Pages;
import ua.nure.koshova.finalProject.view.util.right.RightChecker;

import javax.servlet.RequestDispatcher;
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
        if (!RightChecker.isManager(request.getSession())) {
            response.sendRedirect(Pages.ERROR_PAGE_NOT_ENOUGH_RIGTH);
            return;
        }
        String stringIdOrder = request.getParameter("idOrder");
        Long idOrder = Long.valueOf(stringIdOrder);
        String oldStatus = request.getParameter("status");
        String newStatus = request.getParameter("newStatus");

        // confirm case
        if (OrderStatus.valueOf(newStatus) == OrderStatus.IN_PROGRESS &&
                OrderStatus.valueOf(oldStatus) == OrderStatus.NEW) {
            orderService.confirmOrder(idOrder);
        }
        // reject case
        else if (OrderStatus.valueOf(newStatus) == OrderStatus.CLOSED &&
                OrderStatus.valueOf(oldStatus) == OrderStatus.NEW) {
            orderService.closeOrder(idOrder);
            // forward
            request.setAttribute("idOrder", idOrder);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher(Pages.REJECT_REASON_PAGE);
            dispatcher.forward(request, response);
        }
        // set bill for crash case
        else if((OrderStatus.valueOf(oldStatus) == OrderStatus.CRASH ||
                OrderStatus.valueOf(oldStatus) == OrderStatus.IN_PROGRESS)  &&
                OrderStatus.valueOf(newStatus) == OrderStatus.CRASH) {
            orderService.crashOrder(idOrder);
            // forward
            request.setAttribute("idOrder", idOrder);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher(Pages.CRASH_BILL_PAGE);
            dispatcher.forward(request, response);
        }
        // return a car
        else if(OrderStatus.valueOf(oldStatus) == OrderStatus.IN_PROGRESS &&
                OrderStatus.valueOf(newStatus) == OrderStatus.CLOSED) {
            orderService.closeOrder(idOrder);
        }

        response.sendRedirect("/ordersList");

    }
}
