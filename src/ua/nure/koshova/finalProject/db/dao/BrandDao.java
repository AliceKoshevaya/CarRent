package ua.nure.koshova.finalProject.db.dao;

import ua.nure.koshova.finalProject.db.dao.util.MySQLConnUtils;
import ua.nure.koshova.finalProject.db.dao.util.Requests;
import ua.nure.koshova.finalProject.db.entity.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BrandDao {
    private static volatile BrandDao instance;

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

    public Long getBrandByName(String name) {
        Long id = null;
        Connection con = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(Requests.SELECT_BRAND_BY_ID);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Brand b = new Brand();

                b.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<Brand> findAllBrands() {
        List<Brand> brands = new ArrayList<>();
        Connection connection = MySQLConnUtils.getMySQLConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Requests.SELECT_ALL_BRAND);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Brand brand = new Brand();

                brand.setId(resultSet.getLong(1));
                brand.setName(resultSet.getString(2));
                brands.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brands;
    }


    public static void main(String[] args) {
        BrandDao brandDao = new BrandDao();
        System.out.println(brandDao.getBrandByName("BMV"));
        System.out.println(brandDao.findAllBrands());
    }
}
