package ua.nure.koshova.finalProject.view.servlet.manager;

import org.apache.log4j.Logger;
import ua.nure.koshova.finalProject.service.OrderService;
import ua.nure.koshova.finalProject.view.constant.Pages;
import ua.nure.koshova.finalProject.view.util.right.RightChecker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/reason"})
public class AddReasonServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(AddReasonServlet.class);

    private OrderService orderService = new OrderService();

    public AddReasonServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!RightChecker.isManager(request.getSession())) {
            response.sendRedirect(Pages.ERROR_PAGE_NOT_ENOUGH_RIGTH);
        }
        String idOrderString = request.getParameter("idOrder");
        LOGGER.debug("Got idOrder parameter as " + idOrderString);
        Long numIdOrder = Long.valueOf(idOrderString);
        String reason = request.getParameter("comment");
        LOGGER.debug("Got reason for reject order parameter as " + reason);
        orderService.updateReason(numIdOrder,reason);
        response.sendRedirect("/ordersList");
    }
}