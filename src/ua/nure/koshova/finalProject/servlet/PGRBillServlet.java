package ua.nure.koshova.finalProject.servlet;

import ua.nure.koshova.finalProject.db.dao.impl.BillDao;
import ua.nure.koshova.finalProject.db.entity.Bill;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bill")
public class PGRBillServlet extends HttpServlet {

    private BillDao billDao = BillDao.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("idBill");
        Long idBill = Long.valueOf(id);
        billDao.updateBill(idBill);
        response.sendRedirect("/bill?idBill=" + idBill);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("idBill");
        Long idBill = Long.valueOf(id);
        Bill bill = billDao.findBillById(idBill);
        request.setAttribute("bill", bill);
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Pages.BILL_PAGE);
        dispatcher.forward(request, response);
    }
}
