package ua.nure.koshova.finalProject.dao;

import ua.nure.koshova.finalProject.entity.Role;

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

    public List<Role> findAllRoles() throws SQLException {
        List<Role> rolesList = new ArrayList<Role>();
        Connection connection = MySQLConnUtils.getMySQLConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from roles");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Role role = new Role();
            role.setId(resultSet.getLong(1));
            role.setName(resultSet.getString(2));
            rolesList.add(role);
        }
        return rolesList;
    }

    public Role findRoleByName(String role) throws SQLException {
        Connection connection = MySQLConnUtils.getMySQLConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select id, role_name from roles where role_name =" + role);
        ResultSet resultSet = preparedStatement.executeQuery();
        Role rol = new Role();
        while (resultSet.next()) {
            rol.setId(resultSet.getLong(1));
            rol.setName(resultSet.getString(2));
        }
        return rol;
    }

    public static void main(String[] args) throws SQLException {
        RolesDao r = new RolesDao();
        System.out.println(r.findAllRoles());
    }
}
