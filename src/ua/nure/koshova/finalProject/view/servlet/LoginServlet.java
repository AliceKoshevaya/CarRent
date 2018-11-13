package ua.nure.koshova.finalProject.view.servlet;


import org.apache.log4j.Logger;
import ua.nure.koshova.finalProject.db.entity.Role;
import ua.nure.koshova.finalProject.db.entity.Roles;
import ua.nure.koshova.finalProject.db.entity.User;
import ua.nure.koshova.finalProject.service.UserService;
import ua.nure.koshova.finalProject.view.constant.Pages;
import ua.nure.koshova.finalProject.view.util.right.RightChecker;
import ua.nure.koshova.finalProject.view.util.validator.LoginValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    private UserService userService = new UserService();

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Pages.LOGIN_PAGE);

        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        String errorMessage = LoginValidator.validate(login, password);

        if (!errorMessage.isEmpty()) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher(Pages.LOGIN_PAGE).forward(request, response);
        } else {
            User user = userService.registeredUser(login, password);
            if(user.getName() == null) {
                String errorMessageLogin = "User does not exist";
                request.setAttribute("errorMessageLogin", errorMessageLogin);
                request.getRequestDispatcher(Pages.LOGIN_PAGE).forward(request, response);
                return;
            }
            Role role = userService.getUserRole(user.getId());
            user.setRole(role);
            request.getSession().setAttribute(RightChecker.ATTRIBUTE_USER, user);

            LOGGER.debug("User " + user.getLogin() + " has been logged in as " + role.getName());

            if (role.getName().equals(Roles.administrator)) {
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher(Pages.ADMIN_PAGE_CAR);
                dispatcher.forward(request, response);
            } else if (role.getName().equals(Roles.manager)) {
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher(Pages.LIST_ORDER_PAGE);
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher(Pages.HOMEPAGE);
                dispatcher.forward(request, response);
            }
        }


    }

}
