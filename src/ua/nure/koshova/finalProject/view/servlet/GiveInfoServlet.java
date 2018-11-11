package ua.nure.koshova.finalProject.view.servlet;

import ua.nure.koshova.finalProject.view.constant.Pages;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/info"})
public class GiveInfoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public GiveInfoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Pages.INFO_PAGE);

        dispatcher.forward(request, response);

    }
}
