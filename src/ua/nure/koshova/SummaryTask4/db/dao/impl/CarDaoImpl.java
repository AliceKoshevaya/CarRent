package ua.nure.koshova.SummaryTask4.db.dao.impl;


import org.apache.log4j.Logger;
import ua.nure.koshova.SummaryTask4.db.dao.CarDao;
import ua.nure.koshova.SummaryTask4.db.dao.util.DatabaseRequests;
import ua.nure.koshova.SummaryTask4.db.dao.util.DatabaseUtils;
import ua.nure.koshova.SummaryTask4.db.entity.Brand;
import ua.nure.koshova.SummaryTask4.db.entity.Car;
import ua.nure.koshova.SummaryTask4.db.entity.ClassCar;
import ua.nure.koshova.SummaryTask4.db.entity.Status;
import ua.nure.koshova.SummaryTask4.db.exception.CloseResourcesException;
import ua.nure.koshova.SummaryTask4.db.exception.QueryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {


    private static final Logger LOGGER = Logger.getLogger(CarDaoImpl.class);

    private static final String ERROR_MESSAGE_GET_CAR_BY_ID = "Can't select car by id (id = %d)";
    private static final String ERROR_MESSAGE_SELECT_CAR_BY_CLASS_AND_BRAND = "Can't select car by id (sortField = %s sortOrder = %s idBrand%d idClass%d)";
    private static final String ERROR_MESSAGE_SELECT_CAR_BY_CLASS = "Can't select car by id (sortField = %s sortOrder = %s idClass%d)";
    private static final String ERROR_MESSAGE_SELECT_CAR_BY_BRAND = "Can't select car by id (sortField = %s sortOrder = %s idBrand%d)";
    private static final String ERROR_MESSAGE_CREATE_CAR = "Can't create a new car";
    private static final String ERROR_MESSAGE_DELETE_CAR = "Can't delete a car";
    private static final String ERROR_MESSAGE_UPDATE_CAR = "Can't update a new car";
    private static final String ERROR_MESSAGE_SELECT_ALL_CARS = "Can't select all cars";
    private static volatile CarDaoImpl instance;

    private CarDaoImpl() {

    }

    public static CarDaoImpl getInstance() {
        CarDaoImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (CarDaoImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CarDaoImpl();
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
        Connection connection = DatabaseUtils.getConnection();

        ResultSet generatedKeys = null;

        if (car != null) {
            try (PreparedStatement ps = connection.prepareStatement(DatabaseRequests.INSERT_CAR, Statement.RETURN_GENERATED_KEYS)) {
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
                DatabaseUtils.closeResultSet(generatedKeys);
                DatabaseUtils.closeConnection(connection);
            }
        }
        return id;
    }

    public void deleteCar(Long id) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.DELETE_CAR)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(ERROR_MESSAGE_DELETE_CAR, ex);
            throw new QueryException(ERROR_MESSAGE_DELETE_CAR, ex);
        } finally {
            DatabaseUtils.closeConnection(connection);
        }
    }

    public void updateCar(String name,
                          int price,
                          String govNumber,
                          String status,
                          Long idBrand,
                          Long idClass,
                          Long id) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.UPDATE_CAR)) {
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
        } finally {
            DatabaseUtils.closeConnection(connection);
        }
    }

    /**
     * Случай, когда не пришёл ни один параметр
     *
     * @return
     */
    public List<Car> findAllCars(String sortField, String sortOrder) throws QueryException, CloseResourcesException {
        List<Car> carList = new ArrayList<>();
        Connection connection = DatabaseUtils.getConnection();

        ResultSet resultSet = null;

        String order = (sortOrder != null && sortField != null) ? String.format(" order by %s %s;", sortField, sortOrder) : "";
        String query = DatabaseRequests.SELECT_ALL_CAR + order;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
            DatabaseUtils.closeResultSet(resultSet);
            DatabaseUtils.closeConnection(connection);
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
        Connection connection = DatabaseUtils.getConnection();

        ResultSet resultSet = null;

        String order = String.format(" order by %s %s;", sortField, sortOrder);
        String query = DatabaseRequests.SELECT_GET_CAR_BY_BRAND + order;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            extractCarsListFromResultSet(carList, resultSet);
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_SELECT_CAR_BY_BRAND, sortField, sortOrder, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_SELECT_CAR_BY_BRAND, sortField, sortOrder, id), ex);
        } finally {
            DatabaseUtils.closeResultSet(resultSet);
            DatabaseUtils.closeConnection(connection);
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
        Connection connection = DatabaseUtils.getConnection();

        ResultSet resultSet = null;

        String order = String.format(" order by %s %s", sortField, sortOrder);
        String query = DatabaseRequests.SELECT_GET_CAR_BY_CLASS + order;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            extractCarsListFromResultSet(carList, resultSet);
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_SELECT_CAR_BY_CLASS, sortField, sortOrder, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_SELECT_CAR_BY_CLASS, sortField, sortOrder, id), ex);
        } finally {
            DatabaseUtils.closeResultSet(resultSet);
            DatabaseUtils.closeConnection(connection);
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
        Connection connection = DatabaseUtils.getConnection();

        ResultSet resultSet = null;

        String order = String.format(" order by %s %s", sortField, sortOrder);
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_BY_CLASS_AND_BRAND + order)) {
            preparedStatement.setLong(1, brandId);
            preparedStatement.setLong(2, classId);
            resultSet = preparedStatement.executeQuery();
            extractCarsListFromResultSet(carList, resultSet);
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_SELECT_CAR_BY_CLASS_AND_BRAND, sortField, sortOrder, brandId, classId), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_SELECT_CAR_BY_CLASS_AND_BRAND, sortField, sortOrder, brandId, classId), ex);
        } finally {
            DatabaseUtils.closeResultSet(resultSet);
            DatabaseUtils.closeConnection(connection);
        }
        return carList;
    }

    public Car findCarById(Long id) throws QueryException, CloseResourcesException {
        Connection connection = DatabaseUtils.getConnection();
        Car car = new Car();

        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_CAR_BY_ID)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Brand brand = new Brand();
                ClassCar classCar = new ClassCar();
                car.setClassCar(classCar);
                car.setBrand(brand);

                car.setId(resultSet.getLong(1));
                car.setName(resultSet.getString(2));
                car.setPrice(resultSet.getInt(3));
                car.setStateNumber(resultSet.getString(4));
                car.setStatus(Status.valueOf(resultSet.getString(5).toUpperCase()));
                brand.setName(resultSet.getString(6));
                classCar.setName(resultSet.getString(7));
            }
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_GET_CAR_BY_ID, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_GET_CAR_BY_ID, id), ex);
        } finally {
            DatabaseUtils.closeResultSet(resultSet);
            DatabaseUtils.closeConnection(connection);
        }
        return car;
    }


    private void extractCarsListFromResultSet(List<Car> carList, ResultSet resultSet) throws SQLException {
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
    }
}
