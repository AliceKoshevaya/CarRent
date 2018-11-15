package ua.nure.koshova.SummaryTask4.view.servlet;

import org.apache.log4j.Logger;
import ua.nure.koshova.SummaryTask4.db.entity.*;
import ua.nure.koshova.SummaryTask4.service.Impl.BrandServiceImpl;
import ua.nure.koshova.SummaryTask4.service.Impl.CarServiceImpl;
import ua.nure.koshova.SummaryTask4.service.Impl.ClassServiceImpl;
import ua.nure.koshova.SummaryTask4.view.constant.Pages;
import ua.nure.koshova.SummaryTask4.view.util.right.RightChecker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = {"/carList"})
public class CarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(CarServlet.class);
    private static String PARAM_FILTER_NOOP = "all";

    private CarServiceImpl carServiceImpl = new CarServiceImpl();
    private BrandServiceImpl brandServiceImpl = new BrandServiceImpl();
    private ClassServiceImpl classServiceImpl = new ClassServiceImpl();

    public CarServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        if (!RightChecker.isUser(request.getSession()) && !RightChecker.isManager(request.getSession())
//                && !RightChecker.isAdmin(request.getSession())) {
//            response.sendRedirect(Pages.ERROR_PAGE_LOGIN_PLEASE);
//            return;
//        }
        String idCar = request.getParameter("idCar");
        LOGGER.debug("Got idCar parameter as " + idCar);
        String carName = request.getParameter("carName");
        LOGGER.debug("Got carName parameter as " + carName);
        String price = request.getParameter("price");
        LOGGER.debug("Got price for car parameter as " + price);
        String stateNumber = request.getParameter("stateNumber");
        LOGGER.debug("Got a car stateNumber parameter as " + stateNumber);
        String brand = request.getParameter("brand");
        LOGGER.debug("Got brand car parameter as " + brand);
        String classCar = request.getParameter("class");
        LOGGER.debug("Got class car parameter as " + classCar);

        List<Brand> allBrand = brandServiceImpl.getBrandList();
        request.setAttribute("brands", allBrand);

        List<ClassCar> allClasses = classServiceImpl.getClassList();
        request.setAttribute("classes", allClasses);

        String brandId = request.getParameter("selectBrand");
        String classId = request.getParameter("selectClass");
        LOGGER.debug("Got selectBrand parameter as " + brandId);
        LOGGER.debug("Got selectClass parameter as " + classId);

        String sortField = null;
        String sortOrder = null;
        String sort = request.getParameter("selectSort");
        if (sort != null) {
            String keyValue[] = sort.split(":");
            sortField = keyValue[1];
            sortOrder = keyValue[0];
        }

        List<Car> allCar = carServiceImpl.getCarsList(
                sortField,
                sortOrder,
                PARAM_FILTER_NOOP.equals(brandId) ? null : brandId,
                PARAM_FILTER_NOOP.equals(classId) ? null : classId
        );
        request.setAttribute("cars", allCar);

        User user = (User) request.getSession().getAttribute(RightChecker.ATTRIBUTE_USER);
        boolean isAdmin = user != null && Roles.valueOf(user.getRole().getName()) == Roles.administrator;
        String page = isAdmin ? Pages.ADMIN_PAGE_CAR : Pages.USER_PAGE_CAR;
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

}