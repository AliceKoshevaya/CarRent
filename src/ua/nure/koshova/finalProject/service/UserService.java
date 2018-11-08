package ua.nure.koshova.finalProject.service;

import ua.nure.koshova.finalProject.db.dao.RoleDao;
import ua.nure.koshova.finalProject.db.dao.UserDao;
import ua.nure.koshova.finalProject.db.entity.Role;
import ua.nure.koshova.finalProject.db.entity.Roles;
import ua.nure.koshova.finalProject.db.entity.User;
import java.util.List;

public class UserService {

    private RoleDao rolesDao = RoleDao.getInstance();
    private UserDao usersDao = UserDao.getInstance();

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

    public User registeredUser(String login, String password) {
        User user = usersDao.findUser(login, password);
        return user;
    }

    public List<User> getAllUsers(){
        List<User> users = usersDao.findAllUsers();
        return users;
    }

    public void addUserInfo(Long id, String thirdName, String passSer, String passInfo ){
        usersDao.updateUser(thirdName,passSer,passInfo,id);
    }

    public void makeManager(Long id){
        usersDao.updateUserRole(id);
    }


    public User getUserByLogin(String login){
        User user = usersDao.findUserByLogin(login);
        return user;
    }

    public Role getUserRole(Long id){
        Role role =usersDao.findUserByRole(id);
        return role;

    }

    public void blockUser(Long id){
        usersDao.updateBlockUser(id);
    }

    public void unblockUser(Long id){
        usersDao.updateUnblockUser(id);
    }

    public User checkBlockUser(Long id){
        User user = usersDao.findUserById(id);
        return  user;
    }

}
