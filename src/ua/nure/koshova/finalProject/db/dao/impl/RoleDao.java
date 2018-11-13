package ua.nure.koshova.finalProject.db.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.koshova.finalProject.db.dao.IRoleDao;
import ua.nure.koshova.finalProject.db.dao.util.DatabaseRequests;
import ua.nure.koshova.finalProject.db.dao.util.DatabaseUtils;
import ua.nure.koshova.finalProject.db.entity.Role;
import ua.nure.koshova.finalProject.db.exception.CloseResourcesException;
import ua.nure.koshova.finalProject.db.exception.QueryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDao implements IRoleDao {

    public static final String ERROR_MESSAGE_SELECT_ROLE_BY_NAME = "Can't select user role by name (name = %s)";

    private static final Logger LOGGER = Logger.getLogger(RoleDao.class);

    private static volatile RoleDao instance;

    private RoleDao() {
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

    public Role findRoleByName(String role) throws QueryException, CloseResourcesException {
        Role rol = new Role();
        Connection connection = DatabaseUtils.getConnection();

        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_ROLE_BY_NAME))
        {
            preparedStatement.setString(1, role);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rol.setId(resultSet.getLong(1));
                rol.setName(resultSet.getString(2));
            }
        } catch(SQLException ex){
            LOGGER.error(String.format(ERROR_MESSAGE_SELECT_ROLE_BY_NAME, role), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_SELECT_ROLE_BY_NAME, role), ex);
        } finally{
            DatabaseUtils.closeResultSet(resultSet);
        }
        return rol;
    }

    public static void main(String[] args) {
        RoleDao roleDao = RoleDao.getInstance();
        System.out.println(roleDao.findRoleByName("user"));
    }
}
