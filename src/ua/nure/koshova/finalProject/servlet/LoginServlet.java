package ua.nure.koshova.finalProject.servlet;


import ua.nure.koshova.finalProject.db.entity.Role;
import ua.nure.koshova.finalProject.db.entity.User;
import ua.nure.koshova.finalProject.service.UserService;
import ua.nure.koshova.finalProject.service.exceptions.IllegalLoginParametersException;

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

    private UserService userService = new UserService();

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/LoginView.jsp");

        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
//        User user = userService.getUserByLogin(login);
//        if (userService.registeredUser(login, password) == true) {
//            RequestDispatcher dispatcher
//                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/HomeView.jsp");
//
//            dispatcher.forward(request, response);
//        } else {
//            RequestDispatcher dispatcher
//                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/LoginView.jsp");
//
//            dispatcher.forward(request, response);
//
//        }
        if (login == null || password == null) {
            String errorMessage = "Please fill all the fields!";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        } else {
            try {
                User user = userService.registeredUser(login,password);
                Role role = userService.getUserRole(user.getId());
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("role", role);
//                LOGGER.info("User \"" + user.getLogin() + "\" has been logged in as " + role.getName());

                if (role.getName().equals("administrator")) {
                    RequestDispatcher dispatcher = request.getServletContext()
                            .getRequestDispatcher("/WEB-INF/views/CarListForAdmin.jsp");
                    dispatcher.forward(request, response);
                }else if(role.getName().equals("manager")) {
                    RequestDispatcher dispatcher = request.getServletContext()
                            .getRequestDispatcher("/WEB-INF/views/OrdersList.jsp");
                    dispatcher.forward(request, response);
                }
                else{
                    RequestDispatcher dispatcher = request.getServletContext()
                            .getRequestDispatcher("/WEB-INF/views/HomeView.jsp");
                    dispatcher.forward(request, response);
                }

            } catch (IllegalLoginParametersException e) {
                String errorMessage = e.getMessage();
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(request, response);
            }
        }


    }

}
