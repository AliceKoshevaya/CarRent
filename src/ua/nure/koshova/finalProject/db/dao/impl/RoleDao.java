package ua.nure.koshova.finalProject.db.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.koshova.finalProject.db.dao.IRoleDao;
import ua.nure.koshova.finalProject.db.dao.util.DatabaseUtils;
import ua.nure.koshova.finalProject.db.dao.util.DatabaseRequests;
import ua.nure.koshova.finalProject.db.entity.Role;
import ua.nure.koshova.finalProject.db.exception.CloseResourcesException;
import ua.nure.koshova.finalProject.db.exception.QueryException;

import java.sql.*;

public class RoleDao implements IRoleDao {

    private static volatile RoleDao instance;

    private static final Logger LOGGER = Logger.getLogger(RoleDao.class);

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

    public Role findRoleByName(String role) throws QueryException, CloseResourcesException{
        Role rol = new Role();
        Connection connection = DatabaseUtils.getConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_ROLE_BY_NAME + role);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rol.setId(resultSet.getLong(1));
                rol.setName(resultSet.getString(2));
            }
        } catch (SQLException ex) {
            LOGGER.error("Can't select user role by name (name = " + role + ")", ex);
            throw new QueryException("Can't select user role by name (name = " + role + ")", ex);
        } finally {
            DatabaseUtils.closeResultSet(resultSet);
        }
        return rol;
    }
}
