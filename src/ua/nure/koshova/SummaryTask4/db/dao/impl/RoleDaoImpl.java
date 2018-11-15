package ua.nure.koshova.SummaryTask4.db.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.koshova.SummaryTask4.db.dao.RoleDao;
import ua.nure.koshova.SummaryTask4.db.dao.util.DatabaseRequests;
import ua.nure.koshova.SummaryTask4.db.dao.util.DatabaseUtils;
import ua.nure.koshova.SummaryTask4.db.entity.Role;
import ua.nure.koshova.SummaryTask4.db.exception.CloseResourcesException;
import ua.nure.koshova.SummaryTask4.db.exception.QueryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDaoImpl implements RoleDao {

    public static final String ERROR_MESSAGE_SELECT_ROLE_BY_NAME = "Can't select user role by name (name = %s)";

    private static final Logger LOGGER = Logger.getLogger(RoleDaoImpl.class);

    private static volatile RoleDaoImpl instance;

    private RoleDaoImpl() {
    }

    public static RoleDaoImpl getInstance() {
        RoleDaoImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (CarDaoImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RoleDaoImpl();
                }
            }
        }
        return localInstance;
    }

    public Role findRoleByName(String roleString) throws QueryException, CloseResourcesException {
        Role role = new Role();
        Connection connection = DatabaseUtils.getConnection();

        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(DatabaseRequests.SELECT_ROLE_BY_NAME))
        {
            preparedStatement.setString(1, roleString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role.setId(resultSet.getLong(1));
                role.setName(resultSet.getString(2));
            }
        } catch(SQLException ex){
            LOGGER.error(String.format(ERROR_MESSAGE_SELECT_ROLE_BY_NAME, roleString), ex);
            throw new QueryException(String.format(ERROR_MESSAGE_SELECT_ROLE_BY_NAME, roleString), ex);
        } finally{
            DatabaseUtils.closeResultSet(resultSet);
            DatabaseUtils.closeConnection(connection);
        }
        return role;
    }

}
