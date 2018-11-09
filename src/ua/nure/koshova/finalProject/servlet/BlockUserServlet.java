package ua.nure.koshova.finalProject.servlet;

import ua.nure.koshova.finalProject.db.entity.User;
import ua.nure.koshova.finalProject.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/block"})
public class BlockUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService = new UserService();

    public BlockUserServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idUser = request.getParameter("idUser");
        Long id = Long.valueOf(idUser);
        User user = userService.checkBlockUser(id);
        if(user.isBlock()){
            userService.unblockUser(id);
        }else {
            userService.blockUser(id);
        }
        response.sendRedirect("/userList");
    }
}