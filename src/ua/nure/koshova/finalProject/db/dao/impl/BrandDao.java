package ua.nure.koshova.finalProject.db.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.koshova.finalProject.db.dao.IBrandDao;
import ua.nure.koshova.finalProject.db.dao.util.DatabaseUtils;
import ua.nure.koshova.finalProject.db.dao.util.DatabaseRequests;
import ua.nure.koshova.finalProject.db.entity.Brand;
import ua.nure.koshova.finalProject.db.exception.CloseResourcesException;
import ua.nure.koshova.finalProject.db.exception.QueryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BrandDao implements IBrandDao {

    private static volatile BrandDao instance;

    private static final Logger LOGGER = Logger.getLogger(BrandDao.class);

    public static final String ERROR_MESSAGE_SELECT_ALL_BRANDS = "Can't select all brand ";


    private BrandDao() {

    }

    public static BrandDao getInstance() {
        BrandDao localInstance = instance;
        if (localInstance == null) {
            synchronized (BrandDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new BrandDao();
                }
            }
        }
        return localInstance;
    }

    public Brand getBrandById(Long id) throws QueryException, CloseResourcesException {
        Connection con = DatabaseUtils.getConnection();
        Brand b = new Brand();

        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(DatabaseRequests.SELECT_BRAND_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                b.setName(resultSet.getString(1));
                b.setId(id);
            }
        } catch (SQLException ex) {
            LOGGER.error("Can't select brand car by id (id = " + id + ")", ex);
            throw new QueryException("Can't select brand car by id (id = " + id + ")", ex);
        } finally {
            DatabaseUtils.closeResultSet(resultSet);
        }
        return b;
    }

    public Long getBrandByName(String name) throws QueryException, CloseResourcesException{
        Connection con = DatabaseUtils.getConnection();

        Long id = null;
        ResultSet resultSet = null;

        Brand b = new Brand();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(DatabaseRequests.SELECT_BRAND_BY_NAME);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                b.setId(resultSet.getLong(1));
            }
        } catch (SQLException ex) {
            LOGGER.error("Can't select car brand by name (name = " + name + ")", ex);
            throw new QueryException("Can't select car brand by name (name = " + name + ")", ex);
        } finally {
            DatabaseUtils.closeResultSet(resultSet);
        }
        id = b.getId();
        return id;
    }

    public List<Brand> findAllBrands() throws QueryException, CloseResourcesException{
        List<Brand> brands = new ArrayList<>();

        Connection connection = DatabaseUtils.getConnection();

        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_ALL_BRAND);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Brand brand = new Brand();

                brand.setId(resultSet.getLong(1));
                brand.setName(resultSet.getString(2));
                brands.add(brand);
            }
        } catch (SQLException ex) {
            LOGGER.error(ERROR_MESSAGE_SELECT_ALL_BRANDS, ex);
            throw new QueryException(ERROR_MESSAGE_SELECT_ALL_BRANDS, ex);
        } finally {
            DatabaseUtils.closeResultSet(resultSet);
        }
        return brands;
    }

}
