package ua.nure.koshova.finalProject.service;

import ua.nure.koshova.finalProject.db.dao.CarsDao;
import ua.nure.koshova.finalProject.db.entity.Car;

import java.util.List;

public class CarService {

    private CarsDao carDao  = CarsDao.getInstance();

    public List<Car> findAllCar() {
        return carDao.findAllCars();
    }
}
