package ua.nure.koshova.SummaryTask4.view.servlet.admin;

import ua.nure.koshova.SummaryTask4.service.Impl.UserServiceImpl;
import ua.nure.koshova.SummaryTask4.view.util.right.RightChecker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/makeManager"})
public class MakeManagerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserServiceImpl userServiceImpl = new UserServiceImpl();

    public MakeManagerServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!RightChecker.isAdmin(request.getSession())) {
            response.sendRedirect("/ErrorRightsPage.jsp");
            return;
        }
        String stringIdUser = request.getParameter("idUser");
        Long idUser = Long.valueOf(stringIdUser);
        userServiceImpl.makeManager(idUser);
        response.sendRedirect("/userList");
    }
}

