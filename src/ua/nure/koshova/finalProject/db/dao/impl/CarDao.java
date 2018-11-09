package ua.nure.koshova.finalProject.db.dao.impl;


import org.apache.log4j.Logger;
import ua.nure.koshova.finalProject.db.dao.ICarDao;
import ua.nure.koshova.finalProject.db.dao.util.MySQLConnUtils;
import ua.nure.koshova.finalProject.db.dao.util.RequestsToDB;
import ua.nure.koshova.finalProject.db.entity.Brand;
import ua.nure.koshova.finalProject.db.entity.Car;
import ua.nure.koshova.finalProject.db.entity.ClassCar;
import ua.nure.koshova.finalProject.db.entity.Status;
import ua.nure.koshova.finalProject.db.exception.CloseResourcesException;
import ua.nure.koshova.finalProject.db.exception.QueryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDao implements ICarDao {

    public static final String ERROR_MESSAGE_CREATE_CAR = "Can't create a new car";
    public static final String ERROR_MESSAGE_DELETE_CAR = "Can't delete a car";
    public static final String ERROR_MESSAGE_UPDATE_CAR = "Can't update a new car";
    public static final String ERROR_MESSAGE_SELECT_ALL_CARS = "Can't select all cars";

    private static final Logger LOGGER = Logger.getLogger(CarDao.class);
    private static volatile CarDao instance;

    private CarDao() {

    }

    public static CarDao getInstance() {
        CarDao localInstance = instance;
        if (localInstance == null) {
            synchronized (CarDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CarDao();
                }
            }
        }
        return localInstance;
    }

    ////////////////////
    // update methods //
    ////////////////////

    public Long createCar(Car car) throws CloseResourcesException, QueryException {
        Long id = null;
        Connection con = MySQLConnUtils.getMySQLConnection();

        ResultSet generatedKeys = null;

        if (car != null) {
            try (PreparedStatement ps = con.prepareStatement(RequestsToDB.INSERT_CAR, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, car.getName());
                ps.setInt(2, car.getPrice());
                ps.setString(3, car.getStateNumber());
                ps.setString(4, car.getStatus().toString());
                ps.setLong(5, car.getBrand().getId());
                ps.setLong(6, car.getClassCar().getId());
                ps.executeUpdate();

                generatedKeys = ps.getGeneratedKeys();

                if (null != generatedKeys && generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                }

            } catch (SQLException ex) {
                LOGGER.error(ERROR_MESSAGE_CREATE_CAR, ex);
                throw new QueryException(ERROR_MESSAGE_CREATE_CAR, ex);
            } finally {
                MySQLConnUtils.closeResultSet(generatedKeys);
            }
        }
        return id;
    }

    public void deleteCar(Long id) throws QueryException, CloseResourcesException {
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.DELETE_CAR);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(ERROR_MESSAGE_DELETE_CAR, ex);
            throw new QueryException(ERROR_MESSAGE_DELETE_CAR, ex);
        }
    }

    public void updateCar(String name,
                          int price,
                          String govNumber,
                          String status,
                          Long idBrand,
                          Long idClass,
                          Long id) throws QueryException, CloseResourcesException {
        Connection con = MySQLConnUtils.getMySQLConnection();

        try {
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.UPDATE_CAR);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, price);
            preparedStatement.setString(3, govNumber);
            preparedStatement.setString(4, status);
            preparedStatement.setLong(5, idBrand);
            preparedStatement.setLong(6, idClass);
            preparedStatement.setLong(7, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(ERROR_MESSAGE_UPDATE_CAR, ex);
            throw new QueryException(ERROR_MESSAGE_DELETE_CAR, ex);
        }
    }

    /**
     * Случай, когда не пришёл ни один параметр
     *
     * @return
     */
    public List<Car> findAllCars() throws QueryException, CloseResourcesException {
        List<Car> carList = new ArrayList<>();
        Connection connection = MySQLConnUtils.getMySQLConnection();

        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(RequestsToDB.SELECT_ALL_CAR);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                Brand brand = new Brand();
                ClassCar classCar = new ClassCar();
                car.setBrand(brand);
                car.setClassCar(classCar);

                car.setId(resultSet.getLong(1));
                car.setName(resultSet.getString(2));
                car.setPrice(resultSet.getInt(3));
                car.setStateNumber(resultSet.getString(4));
                car.setStatus(Status.valueOf(resultSet.getString(5).toUpperCase()));

                brand.setId(resultSet.getLong(6));
                brand.setName(resultSet.getString(7));

                classCar.setId(resultSet.getLong(8));
                classCar.setName(resultSet.getString(9));

                carList.add(car);
            }
        } catch (SQLException ex) {
            LOGGER.error(ERROR_MESSAGE_SELECT_ALL_CARS, ex);
            throw new QueryException(ERROR_MESSAGE_SELECT_ALL_CARS, ex);
        } finally {
            MySQLConnUtils.closeResultSet(resultSet);
        }
        return carList;
    }

    /**
     * Случай, когда пришёл только бренд
     *
     * @return
     */
    public List<Car> getCarByBrand(String sortField, String sortOrder, long id)
            throws QueryException, CloseResourcesException {
        List<Car> carList = new ArrayList<Car>();
        Connection con = MySQLConnUtils.getMySQLConnection();

        ResultSet resultSet = null;

        try {
            String order = String.format(" order by %s %s", sortField, sortOrder);
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.SELECT_GET_CAR_BY_BRAND + order);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                ClassCar classCar = new ClassCar();
                car.setClassCar(classCar);

                car.setId(resultSet.getLong(1));
                car.setName(resultSet.getString(2));
                car.setPrice(resultSet.getInt(3));
                car.setStateNumber(resultSet.getString(4));
                car.setStatus(Status.valueOf(resultSet.getString(5).toUpperCase()));

                classCar.setName(resultSet.getString(6));
                carList.add(car);
            }
        } catch (SQLException ex) {
            LOGGER.error("Can't select car by id (sortField = " + sortField
                    + " sortOrder = " + sortOrder + " idBrand" + id + ")", ex);
            throw new QueryException("Can't select car by id (sortField = " + sortField
                    + " sortOrder = " + sortOrder + " idBrand" + id + ")", ex);
        } finally {
            MySQLConnUtils.closeResultSet(resultSet);
        }
        return carList;
    }

    /**
     * Случай, когда пришёл только класс
     *
     * @return
     */
    public List<Car> getCarByClass(String sortField, String sortOrder, Long id)
            throws QueryException, CloseResourcesException {
        List<Car> carList = new ArrayList<>();
        Connection con = MySQLConnUtils.getMySQLConnection();

        ResultSet resultSet = null;

        try {
            String order = String.format(" order by %s %s", sortField, sortOrder);
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.SELECT_GET_CAR_BY_CLASS + order);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car c = new Car();
                Brand brand = new Brand();
                ClassCar classCar = new ClassCar();
                c.setClassCar(classCar);
                c.setBrand(brand);

                c.setId(resultSet.getLong(1));
                c.setName(resultSet.getString(2));
                c.setPrice(resultSet.getInt(3));
                c.setStateNumber(resultSet.getString(4));
                c.setStatus(Status.valueOf(resultSet.getString(5).toUpperCase()));
                brand.setName(resultSet.getString(6));
                classCar.setName(resultSet.getString(7));
                carList.add(c);
            }
        } catch (SQLException ex) {
            LOGGER.error("Can't select car by id (sortField = " + sortField
                    + " sortOrder = " + sortOrder + " idClass" + id + ")", ex);
            throw new QueryException("Can't select car by id (sortField = " + sortField
                    + " sortOrder = " + sortOrder + " idClass" + id + ")", ex);
        } finally {
            MySQLConnUtils.closeResultSet(resultSet);
        }
        return carList;
    }

    /**
     * Случай, когда пришли оба
     *
     * @return
     */
    public List<Car> getCarByClassBrand(String sortField, String sortOrder, long brandId, long classId)
            throws QueryException, CloseResourcesException {
        List<Car> carList = new ArrayList<>();
        Connection con = MySQLConnUtils.getMySQLConnection();

        ResultSet resultSet = null;

        try {
            String order = String.format(" order by %s %s", sortField, sortOrder);
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.SELECT_BY_CLASS_AND_BRAND + order);
            preparedStatement.setLong(1, brandId);
            preparedStatement.setLong(2, classId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car c = new Car();
                Brand brand = new Brand();
                ClassCar classCar = new ClassCar();
                c.setClassCar(classCar);
                c.setBrand(brand);

                c.setId(resultSet.getLong(1));
                c.setName(resultSet.getString(2));
                c.setPrice(resultSet.getInt(3));
                c.setStateNumber(resultSet.getString(4));
                c.setStatus(Status.valueOf(resultSet.getString(5).toUpperCase()));
                brand.setName(resultSet.getString(6));
                classCar.setName(resultSet.getString(7));
                carList.add(c);
            }
        } catch (SQLException ex) {
            LOGGER.error("Can't select car by id (sortField = " + sortField
                    + " sortOrder = " + sortOrder + " idBrand" + brandId + " idClass" + classId + ")", ex);
            throw new QueryException("Can't select car by id (sortField = " + sortField
                    + " sortOrder = " + sortOrder + " idBrand" + brandId + " idClass" + classId + ")", ex);
        } finally {
            MySQLConnUtils.closeResultSet(resultSet);
        }
        return carList;
    }

    public Car findCarById(Long id) throws QueryException, CloseResourcesException {
        Connection connection = MySQLConnUtils.getMySQLConnection();
        Car c = new Car();

        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(RequestsToDB.SELECT_CAR_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Brand brand = new Brand();
                ClassCar classCar = new ClassCar();
                c.setClassCar(classCar);
                c.setBrand(brand);

                c.setId(resultSet.getLong(1));
                c.setName(resultSet.getString(2));
                c.setPrice(resultSet.getInt(3));
                c.setStateNumber(resultSet.getString(4));
                c.setStatus(Status.valueOf(resultSet.getString(5).toUpperCase()));
                brand.setName(resultSet.getString(6));
                classCar.setName(resultSet.getString(7));
            }
        } catch (SQLException ex) {
            LOGGER.error("Can't select car by id (id = " + id + ")", ex);
            throw new QueryException("Can't select car by id (id = " + id + ")", ex);
        } finally {
            MySQLConnUtils.closeResultSet(resultSet);
        }
        return c;
    }

}
