package ua.nure.koshova.SummaryTask4.db.dao;

import ua.nure.koshova.SummaryTask4.db.entity.Role;

public interface RoleDao {

    /**
     * Select role by name
     *
     * @param role
     * @return {@link Role}
     */
    Role findRoleByName(String role);
}
