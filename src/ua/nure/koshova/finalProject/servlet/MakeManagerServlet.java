package ua.nure.koshova.finalProject.servlet;

import ua.nure.koshova.finalProject.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/makeManager"})
public class MakeManagerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService = new UserService();

    public MakeManagerServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stringIdUser = request.getParameter("idUser");
        Long idUser = Long.valueOf(stringIdUser);
        userService.makeManager(idUser);
        response.sendRedirect("/userList");
    }
}

