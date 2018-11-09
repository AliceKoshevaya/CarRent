package ua.nure.koshova.finalProject.db.dao.impl;

import ua.nure.koshova.finalProject.db.dao.IRoleDao;
import ua.nure.koshova.finalProject.db.dao.util.MySQLConnUtils;
import ua.nure.koshova.finalProject.db.dao.util.RequestsToDB;
import ua.nure.koshova.finalProject.db.entity.Role;

import java.sql.*;

public class RoleDao implements IRoleDao {
    private static volatile RoleDao instance;

    private RoleDao(){
    }

    public static RoleDao getInstance() {
        RoleDao localInstance = instance;
        if (localInstance == null) {
            synchronized (CarDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RoleDao();
                }
            }
        }
        return localInstance;
    }

    public Role findRoleByName(String role) {
        Role rol = new Role();
        Connection connection = MySQLConnUtils.getMySQLConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(RequestsToDB.SELECT_ROLE_BY_NAME + role);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rol.setId(resultSet.getLong(1));
                rol.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rol;
    }
}
