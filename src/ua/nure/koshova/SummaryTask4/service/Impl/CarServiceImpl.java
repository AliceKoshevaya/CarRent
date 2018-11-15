package ua.nure.koshova.SummaryTask4.service.Impl;

import ua.nure.koshova.SummaryTask4.db.dao.CarDao;
import ua.nure.koshova.SummaryTask4.db.dao.impl.CarDaoImpl;
import ua.nure.koshova.SummaryTask4.db.entity.Brand;
import ua.nure.koshova.SummaryTask4.db.entity.Car;
import ua.nure.koshova.SummaryTask4.db.entity.ClassCar;
import ua.nure.koshova.SummaryTask4.db.entity.Status;
import ua.nure.koshova.SummaryTask4.service.BrandService;
import ua.nure.koshova.SummaryTask4.service.CarService;
import ua.nure.koshova.SummaryTask4.service.ClassService;

import java.util.List;

public class CarServiceImpl implements CarService{

    private CarDao carDao = CarDaoImpl.getInstance();
    private ClassService classService = new ClassServiceImpl();
    private BrandService brandService = new BrandServiceImpl();

    public List<Car> getCarsList(String sortField, String sortOrder,
                                 String brandId, String classId) {
        List<Car> cars;
        if (brandId == null && classId == null) {
            cars = carDao.findAllCars(sortField, sortOrder);
        } else if (brandId == null && classId != null) {
            Long classIdNum = Long.valueOf(classId);
            cars = carDao.getCarByClass(sortField, sortOrder, classIdNum);
        } else if (brandId != null && classId == null) {
            Long classIdNum = Long.valueOf(brandId);
            cars = carDao.getCarByBrand(sortField, sortOrder, classIdNum);
        } else {
            Long brandIdNum = Long.valueOf(brandId);
            Long classIdNum = Long.valueOf(classId);
            cars = carDao.getCarByClassBrand(sortField, sortOrder, brandIdNum, classIdNum);
        }
        return cars;
    }

    public Car getCarById(Long id){
        return carDao.findCarById(id);
    }

    public void deleteCar(Long id){
        carDao.deleteCar(id);
    }

    public void addNewCar(String name, int price, String stateNumber, Long idBrand, Long idClass){
        Car car = new Car();
        car.setName(name);
        car.setPrice(price);
        car.setStateNumber(stateNumber);
        car.setStatus(Status.valueOf("NEW"));
        Brand brand = brandService.getBrandById(idBrand);
        car.setBrand(brand);
        ClassCar classCar = classService.getClassById(idClass);
        car.setClassCar(classCar);
        carDao.createCar(car);
    }

    public void updateCar(Long id ,String name, int price,String status, String stateNumber, Long idBrand, Long idClass){
        carDao.updateCar(name,price,stateNumber,status,idBrand,idClass,id);
    }
}
