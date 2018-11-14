package ua.nure.koshova.finalProject.view.servlet;

import org.apache.log4j.Logger;
import ua.nure.koshova.finalProject.db.dao.impl.BillDao;
import ua.nure.koshova.finalProject.db.entity.Bill;
import ua.nure.koshova.finalProject.view.constant.Pages;

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

    private BillDao billDao = BillDao.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("idBill");
        LOGGER.debug("Got id bill parameter as " + id);
        Long idBill = Long.valueOf(id);
        billDao.updateBill(idBill);
        response.sendRedirect("/bill?idBill=" + idBill);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stringIdBill = request.getParameter("idBill");
        Long idBill = Long.valueOf(stringIdBill);
        Bill bill = billDao.findBillById(idBill);
        request.setAttribute("bill", bill);
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Pages.BILL_PAGE);
        dispatcher.forward(request, response);
    }
}
