package ua.nure.koshova.finalProject.servlet;

import ua.nure.koshova.finalProject.service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/reason"})
public class AddReasonServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderService orderService = new OrderService();

    public AddReasonServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Object idUser = request.getSession().getAttribute("idOrder");
        Long id = ((Number) idUser).longValue();
        String reason = request.getParameter("comment");
        orderService.updateReason(id,reason);
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher(Pages.LIST_ORDER_PAGE);
        dispatcher.forward(request, response);
    }
}