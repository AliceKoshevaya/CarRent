package ua.nure.koshova.SummaryTask4.service.Impl;

import ua.nure.koshova.SummaryTask4.db.dao.RoleDao;
import ua.nure.koshova.SummaryTask4.db.dao.UserDao;
import ua.nure.koshova.SummaryTask4.db.dao.impl.RoleDaoImpl;
import ua.nure.koshova.SummaryTask4.db.dao.impl.UserDaoImpl;
import ua.nure.koshova.SummaryTask4.db.entity.Role;
import ua.nure.koshova.SummaryTask4.db.entity.Roles;
import ua.nure.koshova.SummaryTask4.db.entity.User;
import ua.nure.koshova.SummaryTask4.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private RoleDao rolesDao = RoleDaoImpl.getInstance();
    private UserDao usersDao = UserDaoImpl.getInstance();


    public User createNewUser(String login,
                              String password,
                              String name,
                              String lastName,
                              String thirdName) {

        Role role = new Role();
        role.setName(Roles.user.toString());
        role = rolesDao.findRoleByName(Roles.user.toString());
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

    public User registeredUser(String login, String password) {
        return usersDao.findUser(login, password);
    }

    public List<User> getAllUsers(){
        return usersDao.findAllUsers();
    }

    public void addUserInfo(Long id, String thirdName, String passSer, String passInfo ){
        usersDao.updateUser(thirdName,passSer,passInfo,id);
    }

    public void makeManager(Long id){
        usersDao.updateUserRole(id);
    }


    public User getUserByLogin(String login){
        return usersDao.findUserByLogin(login);
    }

    public Role getUserRole(Long id){
        return usersDao.findRoleByUser(id);

    }

    public void blockUser(Long id){
        usersDao.updateBlockUser(id);
    }

    public void unblockUser(Long id){
        usersDao.updateUnblockUser(id);
    }

    public User checkBlockUser(Long id){
        return usersDao.findUserById(id);
    }

    public User getUserByPassportSerial(String seria){
        return usersDao.findUserByPassportSeria(seria);
    }

}
