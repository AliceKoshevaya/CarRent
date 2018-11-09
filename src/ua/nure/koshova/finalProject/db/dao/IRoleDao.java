package ua.nure.koshova.finalProject.db.dao;

import ua.nure.koshova.finalProject.db.entity.Role;

public interface IRoleDao {

    /**
     * Select role by name
     *
     * @param role
     * @return {@link Role}
     */
    Role findRoleByName(String role);
}
