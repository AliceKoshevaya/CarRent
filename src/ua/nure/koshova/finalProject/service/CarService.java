package ua.nure.koshova.finalProject.service;

import ua.nure.koshova.finalProject.dao.CarsDao;
import ua.nure.koshova.finalProject.entity.Car;

import java.util.List;

public class CarService {

    private CarsDao carDao  = CarsDao.getInstance();

    public List<Car> findAllCar() {
        return carDao.findAllCars();
    }
}
