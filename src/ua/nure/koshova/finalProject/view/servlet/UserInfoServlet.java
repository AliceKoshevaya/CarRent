package ua.nure.koshova.finalProject.view.servlet;

import ua.nure.koshova.finalProject.db.entity.Order;
import ua.nure.koshova.finalProject.db.entity.User;
import ua.nure.koshova.finalProject.service.Impl.OrderServiceImpl;
import ua.nure.koshova.finalProject.view.constant.Pages;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/userInfo" })
public class UserInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserInfoServlet() {
        super();
    }

    OrderServiceImpl orderServiceImpl = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        if(user != null) {
            Long idUser = user.getId();
            List<Order> allOrders = orderServiceImpl.findAllOrdersByUser(idUser);
            request.setAttribute("orders", allOrders);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher(Pages.USER_INFO);
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher(Pages.USER_INFO);
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}