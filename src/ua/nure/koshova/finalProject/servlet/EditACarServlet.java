package ua.nure.koshova.finalProject.servlet;

import ua.nure.koshova.finalProject.db.entity.Car;
import ua.nure.koshova.finalProject.db.entity.Status;
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

@WebServlet("/editCar")
public class EditACarServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    private CarService carService = new CarService();
    private BrandService brandService = new BrandService();
    private ClassService classService = new ClassService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("carId");
        Long idCar =Long.valueOf(id);
        String carName = request.getParameter("name");
        String price = request.getParameter("price");
        Integer priceForCar = Integer.valueOf(price);
        String stateNumber = request.getParameter("stateNumber");
        String brand = request.getParameter("brand");
        Long idBrand = brandService.getBrandByName(brand);
        String classCar = request.getParameter("class");
        Long idClass = classService.getClassByName(classCar);
        String status = request.getParameter("status");
        carService.updateCar(idCar,carName,priceForCar,status,stateNumber,idBrand,idClass);
        response.sendRedirect("/carList");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("carId");
        Long idCar = Long.valueOf(id);
        Car car = carService.getCarById(idCar);
        request.setAttribute("car", car);
        request.setAttribute("statuses", Status.values());
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Pages.EDIT_A_CAR_PAGE);

        dispatcher.forward(request, response);

    }
}
