package ua.nure.koshova.SummaryTask4.view.servlet.admin;

import org.apache.log4j.Logger;
import ua.nure.koshova.SummaryTask4.db.entity.User;
import ua.nure.koshova.SummaryTask4.service.Impl.UserServiceImpl;
import ua.nure.koshova.SummaryTask4.view.constant.Pages;
import ua.nure.koshova.SummaryTask4.view.util.right.RightChecker;

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

    private UserServiceImpl userServiceImpl = new UserServiceImpl();

    public BlockUserServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!RightChecker.isAdmin(request.getSession())) {
            response.sendRedirect(Pages.ERROR_PAGE_NOT_ENOUGH_RIGTH);
            return;
        }
        String stringIdUser = request.getParameter("idUser");
        LOGGER.debug("Got idUser parameter as " + stringIdUser);
        Long numIdUser = Long.valueOf(stringIdUser);
        User user = userServiceImpl.checkBlockUser(numIdUser);
        if(user.isBlock()){
            userServiceImpl.unblockUser(numIdUser);
        }else {
            userServiceImpl.blockUser(numIdUser);
        }
        response.sendRedirect("/userList");
    }
}