package ua.nure.koshova.finalProject.view.servlet.admin;


import ua.nure.koshova.finalProject.db.entity.User;
import ua.nure.koshova.finalProject.service.Impl.UserServiceImpl;
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

@WebServlet(urlPatterns = {"/userList"})
public class UsersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserServiceImpl userServiceImpl = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!RightChecker.isAdmin(request.getSession())) {
            response.sendRedirect(Pages.ERROR_PAGE_NOT_ENOUGH_RIGTH);
            return;
        }
        List<User> allUsers = userServiceImpl.getAllUsers();
        request.setAttribute("users", allUsers);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher(Pages.USER_LIST_PAGE);
        dispatcher.forward(request, response);
    }

}