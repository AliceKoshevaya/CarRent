package ua.nure.koshova.finalProject.db.dao;

import ua.nure.koshova.finalProject.db.entity.Car;

import java.util.List;

public interface CarDao {

    /**
     * Create a new car
     *
     * @param {@link Car}
     *
     * @return id car
     */
    Long createCar(Car car);

    /**
     * Delete a car
     *
     * @param id
     */
    void deleteCar(Long id);

    /**
     * Delete a car
     *
     * @param name
     * @param price
     * @param govNumber
     * @param status
     * @param idBrand
     * @param idClass
     * @param id
     */

    void updateCar(String name,
                   int price,
                   String govNumber,
                   String status,
                   Long idBrand,
                   Long idClass,
                   Long id);

    /**
     * Select all cars
     *
     * @return {@link List<Car>}
     */
    List<Car> findAllCars();

    /**
     * Select cars by brand
     *
     * @param sortField
     * @param sortOrder
     * @param id
     *
     * @return {@link List<Car>}
     *
     */

    List<Car> getCarByBrand(String sortField, String sortOrder, long id);

    /**
     * Select cars by class
     *
     * @param sortField
     * @param sortOrder
     * @param id
     *
     * @return {@link List<Car>}
     */

    List<Car> getCarByClass(String sortField, String sortOrder, Long id);

    /**
     * Select cars by brand and class
     *
     * @param sortField
     * @param sortOrder
     * @param brandId
     * @param classId
     *
     * @return {@link List<Car>}
     */

    List<Car> getCarByClassBrand(String sortField, String sortOrder, long brandId, long classId);

    /**
     * Select car by id
     *
     * @param id
     *
     * @return {@link Car}
     */
    Car findCarById(Long id);

}
