package ua.nure.koshova.finalProject.servlet;

import ua.nure.koshova.finalProject.db.entity.Bill;
import ua.nure.koshova.finalProject.service.BillService;

import javax.servlet.RequestDispatcher;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idOrder = request.getParameter("idOrder");
        Long id = Long.valueOf(idOrder);
        String sum = request.getParameter("sum");
        Integer summa = Integer.valueOf(sum);
        billService.createCrashBill(summa,null,id);
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/HomeView.jsp");
        dispatcher.forward(request, response);

    }
}
