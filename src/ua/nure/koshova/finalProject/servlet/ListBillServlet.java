package ua.nure.koshova.finalProject.servlet;


import ua.nure.koshova.finalProject.db.entity.Bill;
import ua.nure.koshova.finalProject.service.BillService;
import ua.nure.koshova.finalProject.service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/billList"})
public class ListBillServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BillService billService = new BillService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String orderId = request.getParameter("idOrder");
        Long id = Long.valueOf(orderId);
        List<Bill> allBills = billService.getAllBillByOrder(id);
        request.setAttribute("bills", allBills);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/ListBill.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}