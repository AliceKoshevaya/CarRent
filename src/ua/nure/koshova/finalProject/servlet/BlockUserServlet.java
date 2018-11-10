package ua.nure.koshova.finalProject.servlet;

import org.apache.log4j.Logger;
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

    private static final Logger LOGGER = Logger.getLogger(BlockUserServlet.class);

    private UserService userService = new UserService();

    public BlockUserServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stringIdUser = request.getParameter("idUser");
        LOGGER.debug("Got idUser parameter as " + stringIdUser);
        Long numIdUser = Long.valueOf(stringIdUser);
        User user = userService.checkBlockUser(numIdUser);
        if(user.isBlock()){
            userService.unblockUser(numIdUser);
        }else {
            userService.blockUser(numIdUser);
        }
        response.sendRedirect("/userList");
    }
}