package ua.nure.koshova.finalProject.service.Impl;

import ua.nure.koshova.finalProject.db.dao.impl.CarDaoImpl;
import ua.nure.koshova.finalProject.db.entity.Brand;
import ua.nure.koshova.finalProject.db.entity.Car;
import ua.nure.koshova.finalProject.db.entity.ClassCar;
import ua.nure.koshova.finalProject.db.entity.Status;
import ua.nure.koshova.finalProject.service.CarService;

import java.util.List;

public class CarServiceImpl implements CarService{


    private CarDaoImpl carDaoImpl = CarDaoImpl.getInstance();
    private ClassServiceImpl classServiceImpl = new ClassServiceImpl();
    private BrandServiceImpl brandServiceImpl = new BrandServiceImpl();

    public List<Car> getCarsList(String sortField, String sortOrder,
                                 String brandId, String classId) {
        List<Car> cars;
        if (brandId == null && classId == null) {
            cars = carDaoImpl.findAllCars();
        } else if (brandId == null && classId != null) {
            Long classIdNum = Long.valueOf(classId);
            cars = carDaoImpl.getCarByClass(sortField, sortOrder, classIdNum);
        } else if (brandId != null && classId == null) {
            Long classIdNum = Long.valueOf(brandId);
            cars = carDaoImpl.getCarByBrand(sortField, sortOrder, classIdNum);
        } else {
            Long brandIdNum = Long.valueOf(brandId);
            Long classIdNum = Long.valueOf(classId);
            cars = carDaoImpl.getCarByClassBrand(sortField, sortOrder, brandIdNum, classIdNum);
        }
        return cars;
    }

    public Car getCarById(Long id){
        return carDaoImpl.findCarById(id);
    }

    public void deleteCar(Long id){
        carDaoImpl.deleteCar(id);
    }

    public void addNewCar(String name, int price, String stateNumber, Long idBrand, Long idClass){
        Car car = new Car();
        car.setName(name);
        car.setPrice(price);
        car.setStateNumber(stateNumber);
        car.setStatus(Status.valueOf("NEW"));
        Brand brand = brandServiceImpl.getBrandById(idBrand);
        car.setBrand(brand);
        ClassCar classCar = classServiceImpl.getClassById(idClass);
        car.setClassCar(classCar);
        carDaoImpl.createCar(car);
    }

    public void updateCar(Long id ,String name, int price,String status, String stateNumber, Long idBrand, Long idClass){
        carDaoImpl.updateCar(name,price,stateNumber,status,idBrand,idClass,id);
    }
}
