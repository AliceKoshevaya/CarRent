package ua.nure.koshova.finalProject.db.dao;

import ua.nure.koshova.finalProject.db.dao.util.MySQLConnUtils;
import ua.nure.koshova.finalProject.db.dao.util.RequestsToDB;
import ua.nure.koshova.finalProject.db.entity.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolesDao {
    private static volatile RolesDao instance;

    private RolesDao(){
    }

    public static RolesDao getInstance() {
        RolesDao localInstance = instance;
        if (localInstance == null) {
            synchronized (CarsDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RolesDao();
                }
            }
        }
        return localInstance;
    }

    public List<Role> findAllRoles(){
        List<Role> rolesList = new ArrayList<Role>();
        Connection connection = MySQLConnUtils.getMySQLConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(RequestsToDB.SELECT_ALL_ROLES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getLong(1));
                role.setName(resultSet.getString(2));
                rolesList.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rolesList;
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

    public static void main(String[] args) {
        RolesDao r = new RolesDao();
        System.out.println(r.findAllRoles());
    }
}
