package ua.nure.koshova.finalProject.db.dao;

import ua.nure.koshova.finalProject.db.dao.util.MySQLConnUtils;
import ua.nure.koshova.finalProject.db.dao.util.RequestsToDB;
import ua.nure.koshova.finalProject.db.entity.ClassCar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassDao {
    private static volatile ClassDao instance;

    private ClassDao() {

    }
    public static ClassDao getInstance() {
        ClassDao localInstance = instance;
        if (localInstance == null) {
            synchronized (ClassDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ClassDao();
                }
            }
        }
        return localInstance;
    }

    public Long getClassByName(String name) {
        Long id = null;
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(RequestsToDB.SELECT_CLASS_BY_ID);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ClassCar classCar = new ClassCar();

                classCar.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<ClassCar> findAllClasses() {
        List<ClassCar> cars = new ArrayList<>();
        Connection connection = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(RequestsToDB.SELECT_ALL_CLASSES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ClassCar classCar = new ClassCar();

                classCar.setId(resultSet.getLong(1));
                classCar.setName(resultSet.getString(2));
                classCar.setCoefficient(resultSet.getInt(3));
                cars.add(classCar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public static void main(String[] args) {
        ClassDao classDao = new ClassDao();
        System.out.println(classDao.getClassByName("Econom"));
    }
}
