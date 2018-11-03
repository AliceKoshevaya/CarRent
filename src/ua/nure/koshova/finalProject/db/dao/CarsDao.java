package ua.nure.koshova.finalProject.db.dao;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import ua.nure.koshova.finalProject.db.dao.util.MySQLConnUtils;
import ua.nure.koshova.finalProject.db.dao.util.Requests;
import ua.nure.koshova.finalProject.db.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarsDao {

    private static volatile CarsDao instance;

    private CarsDao() {

    }

    public static CarsDao getInstance() {
        CarsDao localInstance = instance;
        if (localInstance == null) {
            synchronized (CarsDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CarsDao();
                }
            }
        }
        return localInstance;
    }

    ////////////////////
    // update methods //
    ////////////////////

    public static void main(String[] args) {
        CarsDao cars = new CarsDao();
        System.out.println(cars.findAllCars());
//        System.out.println(cars.getCarByBrand(3L));
        //       cars.deleteCar(1L);
//        Car car = new Car();
//        car.setCarName("Veyron 16.4");
//        car.setPrice(1100);
//        car.setStateNumber("AX7845CH");
//        car.setStatus(Status.valueOf("RENT"));
//        Brand brand = new Brand();
//        brand.setId(8l);
//        brand.setName("Bugatti");
//        car.setBrand(brand);
//        ClassCar classCar = new ClassCar();
//        classCar.setId(5l);
//        classCar.setName("Lux");
//        car.setClassCar(classCar);
//        System.out.println(cars.createCar(car));
//        System.out.println(cars.findCarById(3L));
    }

    public Long createCar(Car car) {
        Long id = null;
        Connection con = MySQLConnUtils.getMySQLConnection();
        if (car != null) {
            try (PreparedStatement ps = con.prepareStatement(Requests.INSERT_CAR, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, car.getCarName());
                ps.setInt(2, car.getPrice());
                ps.setString(3, car.getStateNumber());
                ps.setString(4, car.getStatus().toString());
                ps.setLong(5, car.getBrand().getId());
                ps.setLong(6, car.getClassCar().getId());
                ps.executeUpdate();

                ResultSet generatedKeys = ps.getGeneratedKeys();

                if (null != generatedKeys && generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                }

            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
        return id;
    }

    public void deleteCar(Long id) {
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(Requests.DELETE_CAR);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCar(String name,
                          int price,
                          String govNumber,
                          String status,
                          Long idBrand,
                          Long idClass) {
        Connection con = MySQLConnUtils.getMySQLConnection();

        try {
            PreparedStatement preparedStatement = con.prepareStatement(Requests.UPDATE_CAR);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, price);
            preparedStatement.setString(1, govNumber);
            preparedStatement.setString(1, status);
            preparedStatement.setLong(1, idBrand);
            preparedStatement.setLong(1, idClass);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Случай, когда не пришёл ни один параметр
     * @return
     */
    public List<Car> findAllCars() {
        List<Car> carList = new ArrayList<>();
        Connection connection = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Requests.SELECT_ALL_CAR);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                Brand brand = new Brand();
                ClassCar classCar = new ClassCar();
                car.setBrand(brand);
                car.setClassCar(classCar);

                car.setId(resultSet.getLong(1));
                car.setCarName(resultSet.getString(2));
                car.setPrice(resultSet.getInt(3));
                car.setStateNumber(resultSet.getString(4));
                car.setStatus(Status.valueOf(resultSet.getString(5).toUpperCase()));

                brand.setId(resultSet.getLong(6));
                brand.setName(resultSet.getString(7));

                classCar.setId(resultSet.getLong(8));
                classCar.setName(resultSet.getString(9));

                carList.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    /**
     * Случай, когда пришёл только бренд
     * @return
     */
    public List<Car> getCarByBrand(String sortField, String sortOrder, long id) {
        List<Car> carList = new ArrayList<Car>();
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            String order = String.format(" order by %s %s", sortField, sortOrder);
            PreparedStatement preparedStatement = con.prepareStatement(Requests.SELECT_GET_CAR_BY_BRAND + order);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                ClassCar classCar = new ClassCar();
                car.setClassCar(classCar);

                car.setId(resultSet.getLong(1));
                car.setCarName(resultSet.getString(2));
                car.setPrice(resultSet.getInt(3));
                car.setStateNumber(resultSet.getString(4));
                car.setStatus(Status.valueOf(resultSet.getString(5).toUpperCase()));

                classCar.setName(resultSet.getString(6));
                carList.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    /**
     * Случай, когда пришёл только класс
     * @return
     */
    public List<Car> getCarByClass(String sortField, String sortOrder, Long id) {
        List<Car> carList = new ArrayList<>();
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            String order = String.format(" order by %s %s", sortField, sortOrder);
            PreparedStatement preparedStatement = con.prepareStatement(Requests.SELECT_GET_CAR_BY_CLASS + order);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car c = new Car();
                Brand brand = new Brand();
                ClassCar classCar = new ClassCar();
                c.setClassCar(classCar);
                c.setBrand(brand);

                c.setId(resultSet.getLong(1));
                c.setCarName(resultSet.getString(2));
                c.setPrice(resultSet.getInt(3));
                c.setStateNumber(resultSet.getString(4));
                c.setStatus(Status.valueOf(resultSet.getString(5).toUpperCase()));
                brand.setName(resultSet.getString(6));
                classCar.setName(resultSet.getString(7));
                carList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    /**
     * Случай, когда пришли оба
     * @return
     */
    public List<Car> getCarByClassBrand(String sortField, String sortOrder, long brandId, long classId) {
        List<Car> carList = new ArrayList<>();
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            String order = String.format(" order by %s %s", sortField, sortOrder);
            PreparedStatement preparedStatement = con.prepareStatement(Requests.SELECT_BY_CLASS_AND_BRAND + order);
            preparedStatement.setLong(1, brandId);
            preparedStatement.setLong(2, classId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car c = new Car();
                Brand brand = new Brand();
                ClassCar classCar = new ClassCar();
                c.setClassCar(classCar);
                c.setBrand(brand);

                c.setId(resultSet.getLong(1));
                c.setCarName(resultSet.getString(2));
                c.setPrice(resultSet.getInt(3));
                c.setStateNumber(resultSet.getString(4));
                c.setStatus(Status.valueOf(resultSet.getString(5).toUpperCase()));
                brand.setName(resultSet.getString(6));
                classCar.setName(resultSet.getString(7));
                carList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    public Car findCarById(Long id){
        Connection connection = MySQLConnUtils.getMySQLConnection();
        Car c = new Car();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Requests.SELECT_CAR_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Brand brand = new Brand();
                ClassCar classCar = new ClassCar();
                c.setClassCar(classCar);
                c.setBrand(brand);

                c.setId(resultSet.getLong(1));
                c.setCarName(resultSet.getString(2));
                c.setPrice(resultSet.getInt(3));
                c.setStateNumber(resultSet.getString(4));
                c.setStatus(Status.valueOf(resultSet.getString(5).toUpperCase()));
                brand.setName(resultSet.getString(6));
                classCar.setName(resultSet.getString(7));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return c;
    }

}
