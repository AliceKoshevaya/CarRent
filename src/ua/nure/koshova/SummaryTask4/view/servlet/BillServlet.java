package ua.nure.koshova.SummaryTask4.view.servlet;

import org.apache.log4j.Logger;
import ua.nure.koshova.SummaryTask4.db.dao.impl.BillDaoImpl;
import ua.nure.koshova.SummaryTask4.db.entity.Bill;
import ua.nure.koshova.SummaryTask4.view.constant.Pages;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bill")
public class BillServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(BillServlet.class);

    private BillDaoImpl billDaoImpl = BillDaoImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("idBill");
        LOGGER.debug("Got id bill parameter as " + id);
        Long idBill = Long.valueOf(id);
        billDaoImpl.updateBill(idBill);
        response.sendRedirect("/bill?idBill=" + idBill);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stringIdBill = request.getParameter("idBill");
        Long idBill = Long.valueOf(stringIdBill);
        Bill bill = billDaoImpl.findBillById(idBill);
        request.setAttribute("bill", bill);
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Pages.BILL_PAGE);
        dispatcher.forward(request, response);
    }
}
