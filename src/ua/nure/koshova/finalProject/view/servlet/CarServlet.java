package ua.nure.koshova.finalProject.view.servlet;

import org.apache.log4j.Logger;
import ua.nure.koshova.finalProject.db.entity.Brand;
import ua.nure.koshova.finalProject.db.entity.Car;
import ua.nure.koshova.finalProject.db.entity.ClassCar;
import ua.nure.koshova.finalProject.service.BrandService;
import ua.nure.koshova.finalProject.service.CarService;
import ua.nure.koshova.finalProject.service.ClassService;
import ua.nure.koshova.finalProject.view.constant.Pages;
import ua.nure.koshova.finalProject.view.util.right.RightChecker;

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

    private CarService carService = new CarService();

    private BrandService brandService = new BrandService();

    private ClassService classService = new ClassService();

    private static final Logger LOGGER = Logger.getLogger(CarServlet.class);

    public CarServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!RightChecker.isUser(request.getSession()) && !RightChecker.isManager(request.getSession())
                && !RightChecker.isAdmin(request.getSession())) {
            response.sendRedirect(Pages.ERROR_PAGE_LOGIN_PLEASE);
            return;
        }
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

        List<ClassCar> allClasses = classService.getClassList();
        request.setAttribute("classes", allClasses);

        List<Brand> allBrand = brandService.getBrandList();
        request.setAttribute("brands", allBrand);

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

        List<Car> allCar = carService.getCarsList(sortField, sortOrder, brandId, classId);
        request.setAttribute("cars", allCar);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher(Pages.USER_PAGE_CAR);
        dispatcher.forward(request, response);
    }

}