package ua.nure.koshova.finalProject.dao;


import ua.nure.koshova.finalProject.Status;
import ua.nure.koshova.finalProject.entity.Brand;
import ua.nure.koshova.finalProject.entity.Car;
import ua.nure.koshova.finalProject.entity.ClassCar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarsDao {

    private static volatile CarsDao instance;

    private CarsDao(){

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

    public static void main(String[] args) throws SQLException {
        CarsDao cars = new CarsDao();
        System.out.println(cars.findAllCars());
        System.out.println(cars.getCarByBrand(3L));
        System.out.println(cars.getCarByClass(3L));
        System.out.println(cars.sortByPrice("desc"));
        System.out.println(cars.sortByName("asc"));
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
    }

    public List<Car> findAllCars() {
        List<Car> carList = new ArrayList<>();
        Connection connection = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select c.id, c.name, c.price, c.gov_number, c.status, b.id, b.name, cl.id, cl.name " +
                    " from cars  AS c LEFT JOIN brand AS b ON c.id_brand = b.id  LEFT JOIN classes AS cl ON c.id_class = cl.id \n" +
                    "ORDER BY c.id;");
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

    public List<Car> getCarByBrand(Long id) throws SQLException {
        List<Car> carList = new ArrayList<Car>();
        Connection con = MySQLConnUtils.getMySQLConnection();
        PreparedStatement preparedStatement = con.prepareStatement("select c.id, c.name, c.price, c.gov_number, c.status, cl.name from cars AS c " +
                "left join classes  cl on c.id_class = cl.id WHERE id_brand = ?;");
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
        return carList;
    }

    public List<Car> getCarByClass(Long Classid) throws SQLException {
        List<Car> carList = new ArrayList<Car>();
        Connection con = MySQLConnUtils.getMySQLConnection();
        PreparedStatement preparedStatement = con.prepareStatement("select c.id, c.name, c.price, c.gov_number, c.status from cars AS c" +
                " WHERE id_class=?;");
        preparedStatement.setLong(1, Classid);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Car c = new Car();
            c.setId(resultSet.getLong(1));
            c.setCarName(resultSet.getString(2));
            c.setPrice(resultSet.getInt(3));
            c.setStateNumber(resultSet.getString(4));
            c.setStatus(Status.valueOf(resultSet.getString(5).toUpperCase()));
            carList.add(c);
        }
        return carList;
    }

    public List<Car> sortByPrice(String sort) throws SQLException {
        List<Car> carList = new ArrayList<Car>();
        Connection connection = MySQLConnUtils.getMySQLConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select c.id, c.name, c.price, c.gov_number, c.status, b.id, b.name,cl.id, " +
                "cl.name,cl.coeff, cl.coeff*c.price as total from cars  AS c LEFT JOIN brand AS b ON c.id_brand = b.id  " +
                "LEFT JOIN classes AS cl ON c.id_class = cl.id order by total " + sort + ";");
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
            classCar.setCoefficient(resultSet.getInt(10));
            car.setTotalPrice(resultSet.getInt(3)*resultSet.getInt(10));
            carList.add(car);
        }
        return carList;
    }

    public List<Car> sortByName(String sortBy) throws SQLException {
        List<Car> carList = new ArrayList<Car>();
        Connection connection = MySQLConnUtils.getMySQLConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select c.id, c.name, c.price, c.gov_number, c.status, b.id, b.name, cl.id, cl.name " +
                " from cars  AS c LEFT JOIN brand AS b ON c.id_brand = b.id  LEFT JOIN classes AS cl ON c.id_class = cl.id \n" +
                "ORDER BY c.name "+ sortBy +";");
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
        return carList;
    }

    public Long createCar(Car car) throws SQLException {
        Long id = null;
        Connection con = MySQLConnUtils.getMySQLConnection();
        if (car != null) {
            try (PreparedStatement ps = con.prepareStatement("insert into cars values (DEFAULT ,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
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

    public void deleteCar(Long id) throws SQLException {
        Connection con = MySQLConnUtils.getMySQLConnection();
        String sql = "DELETE FROM cars WHERE id=?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.close();
        }
    }

    public void updateCar(String name, int price, String govNumber, String status, Long idBrand, Long idClass) throws SQLException {
        Connection con = MySQLConnUtils.getMySQLConnection();
        PreparedStatement preparedStatement = con.prepareStatement("UPDATE cars SET name=?, price=?, " +
                "gov_number=?, status=?, id_brand=?, id_class=? WHERE id=?");
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, price);
        preparedStatement.setString(1, govNumber);
        preparedStatement.setString(1, status);
        preparedStatement.setLong(1, idBrand);
        preparedStatement.setLong(1, idClass);
        preparedStatement.executeUpdate();

    }

    public List<Car> filterByPrice(int start,  int end) throws SQLException {
        List<Car> carList = new ArrayList<Car>();
        Connection connection = MySQLConnUtils.getMySQLConnection();
        if(end > start) {
            PreparedStatement preparedStatement = connection.prepareStatement("select c.id, c.name, c.price, c.gov_number, c.status, b.id, b.name,cl.id, cl.name as total from cars  AS c " +
                    "LEFT JOIN brand AS b ON c.id_brand = b.id  LEFT JOIN classes AS cl ON c.id_class = cl.id " +
                    "WHERE price < " + start + " AND price > " + end + " order by c.id;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                Brand brand = new Brand();
                car.setBrand(brand);
                car.setId(resultSet.getLong(1));
                car.setCarName(resultSet.getString(2));
                car.setPrice(resultSet.getInt(3));
                car.setStateNumber(resultSet.getString(4));
                car.setStatus(Status.valueOf(resultSet.getString(5).toUpperCase()));
                brand.setId(resultSet.getLong(6));
                brand.setName(resultSet.getString(7));
                carList.add(car);
            }
        }
        else {
            //Своё исключение
        }
        return carList;
    }

}
