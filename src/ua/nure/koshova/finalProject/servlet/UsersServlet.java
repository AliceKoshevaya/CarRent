package ua.nure.koshova.finalProject.servlet;


import ua.nure.koshova.finalProject.db.entity.User;
import ua.nure.koshova.finalProject.service.UserService;

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

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> allUsers = userService.getAllUsers();
        request.setAttribute("users", allUsers);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher(Pages.USER_LIST_PAGE);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}