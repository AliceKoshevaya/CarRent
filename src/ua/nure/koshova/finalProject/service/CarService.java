package ua.nure.koshova.finalProject.service;

import ua.nure.koshova.finalProject.db.entity.Car;

import java.util.List;

/**
 * Interface for common car actions
 */
public interface CarService {

    /**
     * Get all cars
     *
     * @param sortField
     * @param sortOrder
     * @param brandId
     * @param classId
     * @return {@Link List<Car>}
     */
    List<Car> getCarsList(String sortField, String sortOrder,
                          String brandId, String classId);

    /**
     * Get car by id
     *
     * @param id
     * @return {@Link Car}
     */
    Car getCarById(Long id);

    /**
     * Delete Car
     *
     * @param id
     */
    void deleteCar(Long id);

    /**
     * Add a new car
     *
     * @param name
     * @param price
     * @param stateNumber
     * @param idBrand
     * @param idClass
     */
    void addNewCar(String name, int price, String stateNumber, Long idBrand, Long idClass);

    /**
     * Update car info
     *
     * @param id
     * @param name
     * @param price
     * @param status
     * @param stateNumber
     * @param idBrand
     * @param idClass
     */
    void updateCar(Long id, String name, int price, String status, String stateNumber, Long idBrand, Long idClass);

}
