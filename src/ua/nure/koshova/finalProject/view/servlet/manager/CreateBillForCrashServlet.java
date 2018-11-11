package ua.nure.koshova.finalProject.view.servlet.manager;

import ua.nure.koshova.finalProject.service.BillService;
import ua.nure.koshova.finalProject.view.constant.Pages;
import ua.nure.koshova.finalProject.view.util.right.RightChecker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/crash"})
public class CreateBillForCrashServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private BillService billService = new BillService();

    public CreateBillForCrashServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!RightChecker.isManager(request.getSession())) {
            response.sendRedirect(Pages.ERROR_PAGE_NOT_ENOUGH_RIGTH);
            return;
        }

        Long idOrder = Long.valueOf(request.getParameter("idOrder"));
        String sum = request.getParameter("sum");
        Integer numSum = Integer.valueOf(sum);
        billService.createCrashBill(numSum, null, idOrder);

        response.sendRedirect("/ordersList");

    }
}
