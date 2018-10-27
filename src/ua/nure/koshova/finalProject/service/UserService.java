package ua.nure.koshova.finalProject.service;

import ua.nure.koshova.finalProject.Roles;
import ua.nure.koshova.finalProject.dao.RolesDao;
import ua.nure.koshova.finalProject.dao.UsersDao;
import ua.nure.koshova.finalProject.entity.Role;
import ua.nure.koshova.finalProject.entity.User;

import java.sql.SQLException;

public class UserService {

    private RolesDao roles;
    private UsersDao u;

    public User createNewUser(String login, String password, String name, String lastName, String thirdName ){

        Role role = null;
        try {
            role = roles.findRoleByName(Roles.user.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setLastName(lastName);
        user.setThirdName(thirdName);
        user.setRole(role);

        u = UsersDao.getInstance();
        u.createUser(user);
        return user;
    }
}
