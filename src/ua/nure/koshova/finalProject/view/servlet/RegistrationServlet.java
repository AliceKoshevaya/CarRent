package ua.nure.koshova.finalProject.view.servlet;

import ua.nure.koshova.finalProject.service.UserService;
import ua.nure.koshova.finalProject.view.constant.Pages;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService = new UserService();

    public RegistrationServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Pages.REG_PAGE);

        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String thirdName = request.getParameter("tname");

        userService.createNewUser(login, password, name, lastName, thirdName);
    }
}
