package ua.nure.koshova.SummaryTask4.db.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.koshova.SummaryTask4.db.dao.ClassDao;
import ua.nure.koshova.SummaryTask4.db.dao.util.DatabaseRequests;
import ua.nure.koshova.SummaryTask4.db.dao.util.DatabaseUtils;
import ua.nure.koshova.SummaryTask4.db.entity.ClassCar;
import ua.nure.koshova.SummaryTask4.db.exception.CloseResourcesException;
import ua.nure.koshova.SummaryTask4.db.exception.QueryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassDaoImpl implements ClassDao {

    private static final Logger LOGGER = Logger.getLogger(ClassDaoImpl.class);

    private static volatile ClassDaoImpl instance;

    private static final String ERROR_MESSAGE_SELECT_CLASS_BY_NAME = "Can't select class car by name (name = %s)";
    private static final String ERROR_MESSAGE_SELECT_CLASS = "Can't select class car by id (id = %d)";
    private static final String ERROR_MESSAGE_SELECT_ALL_CLASSES = "Can't select all classes";

    private ClassDaoImpl() {
    }

    public static ClassDaoImpl getInstance() {
        ClassDaoImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (ClassDaoImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ClassDaoImpl();
                }
            }
        }
        return localInstance;
    }

    public ClassCar getClassById(Long id) throws CloseResourcesException, QueryException {
        ClassCar classCar = new ClassCar();
        Connection connection = DatabaseUtils.getConnection();

        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_CLASS_BY_ID)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                classCar.setName(resultSet.getString(1));
            }
            classCar.setId(id);
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_SELECT_CLASS, id), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_SELECT_CLASS, id), ex);
        } finally {
            DatabaseUtils.closeResultSet(resultSet);
            DatabaseUtils.closeConnection(connection);
        }
        return classCar;
    }

    public List<ClassCar> findAllClasses() throws CloseResourcesException, QueryException {
        List<ClassCar> cars = new ArrayList<>();
        Connection connection = DatabaseUtils.getConnection();

        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_ALL_CLASSES)) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ClassCar classCar = new ClassCar();

                classCar.setId(resultSet.getLong(1));
                classCar.setName(resultSet.getString(2));
                classCar.setCoefficient(resultSet.getInt(3));
                cars.add(classCar);
            }
        } catch (SQLException ex) {
            LOGGER.error(ERROR_MESSAGE_SELECT_ALL_CLASSES, ex);
            throw new QueryException(ERROR_MESSAGE_SELECT_ALL_CLASSES, ex);
        } finally {
            DatabaseUtils.closeResultSet(resultSet);
            DatabaseUtils.closeConnection(connection);
        }
        return cars;
    }

    public Long getClassByName(String name) throws CloseResourcesException, QueryException {
        Connection connection = DatabaseUtils.getConnection();

        Long id = null;
        ResultSet resultSet = null;
        ClassCar classCar = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_CLASS_BY_NAME)) {
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                classCar = new ClassCar();
                classCar.setId(resultSet.getLong(1));
            }
        } catch (SQLException ex) {
            LOGGER.error(String.format(ERROR_MESSAGE_SELECT_CLASS_BY_NAME, name), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_SELECT_CLASS_BY_NAME, name), ex);
        } finally {
            DatabaseUtils.closeResultSet(resultSet);
            DatabaseUtils.closeConnection(connection);
        }

        if (classCar != null) {
            id = classCar.getId();
        }
        return id;
    }
}
