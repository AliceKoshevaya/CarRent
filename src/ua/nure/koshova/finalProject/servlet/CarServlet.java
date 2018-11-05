package ua.nure.koshova.finalProject.servlet;

import ua.nure.koshova.finalProject.db.entity.Brand;
import ua.nure.koshova.finalProject.db.entity.Car;
import ua.nure.koshova.finalProject.db.entity.ClassCar;
import ua.nure.koshova.finalProject.service.BrandService;
import ua.nure.koshova.finalProject.service.CarService;
import ua.nure.koshova.finalProject.service.ClassService;

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

    public CarServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idcar = request.getParameter("idCar");
        String carName = request.getParameter("carName");
        String price = request.getParameter("price");
        String stateNumber = request.getParameter("stateNumber");
        String brand = request.getParameter("brand");
        String classCar = request.getParameter("class");

        List<ClassCar> allClasses = classService.getClassList();
        request.setAttribute("classes", allClasses);

        List<Brand> allBrand = brandService.getBrandList();
        request.setAttribute("brands", allBrand);

        String brandId = request.getParameter("selectBrand");
        String classId = request.getParameter("selectClass");

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
                .getRequestDispatcher("/WEB-INF/views/CarListForAdmin.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}