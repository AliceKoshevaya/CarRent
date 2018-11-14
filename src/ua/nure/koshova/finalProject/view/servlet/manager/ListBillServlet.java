package ua.nure.koshova.finalProject.view.servlet.manager;


import ua.nure.koshova.finalProject.db.entity.Bill;
import ua.nure.koshova.finalProject.service.Impl.BillServiceImpl;
import ua.nure.koshova.finalProject.view.constant.Pages;
import ua.nure.koshova.finalProject.view.util.right.RightChecker;

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

    private BillServiceImpl billServiceImpl = new BillServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!RightChecker.isManager(request.getSession())) {
            response.sendRedirect(Pages.ERROR_PAGE_NOT_ENOUGH_RIGTH);
            return;
        }
        String stringOrderId = request.getParameter("idOrder");
        Long idOrder = Long.valueOf(stringOrderId);
        List<Bill> allBills = billServiceImpl.getAllBillByOrder(idOrder);
        request.setAttribute("bills", allBills);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher(Pages.BILL_LIST_PAGE);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}