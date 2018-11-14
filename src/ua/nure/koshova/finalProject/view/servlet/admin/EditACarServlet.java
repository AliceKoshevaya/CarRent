package ua.nure.koshova.finalProject.view.servlet.admin;

import ua.nure.koshova.finalProject.db.entity.Car;
import ua.nure.koshova.finalProject.db.entity.Status;
import ua.nure.koshova.finalProject.service.Impl.BrandServiceImpl;
import ua.nure.koshova.finalProject.service.Impl.CarServiceImpl;
import ua.nure.koshova.finalProject.service.Impl.ClassServiceImpl;
import ua.nure.koshova.finalProject.view.constant.Pages;
import ua.nure.koshova.finalProject.view.util.right.RightChecker;

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

    private CarServiceImpl carServiceImpl = new CarServiceImpl();
    private BrandServiceImpl brandServiceImpl = new BrandServiceImpl();
    private ClassServiceImpl classServiceImpl = new ClassServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!RightChecker.isAdmin(request.getSession())) {
            response.sendRedirect(Pages.ERROR_PAGE_NOT_ENOUGH_RIGTH);
            return;
        }
        String id = request.getParameter("carId");
        Long idCar =Long.valueOf(id);
        String carName = request.getParameter("name");
        String price = request.getParameter("price");
        Integer priceForCar = Integer.valueOf(price);
        String stateNumber = request.getParameter("stateNumber");
        String brand = request.getParameter("brand");
        Long idBrand = brandServiceImpl.getBrandByName(brand);
        String classCar = request.getParameter("class");
        Long idClass = classServiceImpl.getClassByName(classCar);
        String status = request.getParameter("status");
        carServiceImpl.updateCar(idCar,carName,priceForCar,status,stateNumber,idBrand,idClass);
        response.sendRedirect("/carList");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stringIdCar = request.getParameter("carId");
        Long idCar = Long.valueOf(stringIdCar);
        Car car = carServiceImpl.getCarById(idCar);
        request.setAttribute("car", car);
        request.setAttribute("statuses", Status.values());
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Pages.EDIT_A_CAR_PAGE);

        dispatcher.forward(request, response);

    }
}
