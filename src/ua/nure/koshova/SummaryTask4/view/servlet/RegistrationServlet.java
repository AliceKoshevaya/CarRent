package ua.nure.koshova.SummaryTask4.view.servlet;

import org.apache.log4j.Logger;
import ua.nure.koshova.SummaryTask4.service.Impl.UserServiceImpl;
import ua.nure.koshova.SummaryTask4.view.constant.Pages;
import ua.nure.koshova.SummaryTask4.view.util.validator.RegistrationValidator;

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

    private UserServiceImpl userServiceImpl = new UserServiceImpl();

    private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);

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
        LOGGER.debug("Got login parameter as " + login);
        String password = request.getParameter("password");
        LOGGER.debug("Got password parameter as " + password);
        String name = request.getParameter("fname");
        LOGGER.debug("Got user name parameter as " + name);
        String lastName = request.getParameter("lname");
        LOGGER.debug("Got user last name parameter as " + lastName);
        String thirdName = request.getParameter("tname");
        LOGGER.debug("Got user third name parameter as " + thirdName);
        String errorMessage = RegistrationValidator.validate(login, userServiceImpl.getUserByLogin(login),password,name,lastName);
        if (!errorMessage.isEmpty()) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher(Pages.REG_PAGE).forward(request, response);
        }else {
            userServiceImpl.createNewUser(login, password, name, lastName, thirdName);
            response.sendRedirect("/login");
        }
    }
}
