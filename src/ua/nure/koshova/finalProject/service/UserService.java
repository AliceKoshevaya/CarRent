package ua.nure.koshova.finalProject.service;

import ua.nure.koshova.finalProject.db.dao.RolesDao;
import ua.nure.koshova.finalProject.db.dao.UsersDao;
import ua.nure.koshova.finalProject.db.entity.Role;
import ua.nure.koshova.finalProject.db.entity.Roles;
import ua.nure.koshova.finalProject.db.entity.User;
import java.util.List;

public class UserService {

    private RolesDao rolesDao = RolesDao.getInstance();
    private UsersDao usersDao = UsersDao.getInstance();

    public User createNewUser(String login,
                              String password,
                              String name,
                              String lastName,
                              String thirdName) {

        Role role = rolesDao.findRoleByName(Roles.user.toString());
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setLastName(lastName);
        user.setThirdName(thirdName);
        user.setRole(role);

        usersDao.createUser(user);
        return user;
    }

    public boolean registeredUser(String login, String password) {
        boolean register = usersDao.findUser(login, password);
        boolean isRegister = false;
        if (register == true) {
            isRegister = true;
        }
        return isRegister;
    }

    public List<User> getAllUsers(){
        List<User> users = usersDao.findAllUsers();
        return users;
    }
}
