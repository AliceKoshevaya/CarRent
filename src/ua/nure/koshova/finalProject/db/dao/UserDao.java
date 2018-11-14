package ua.nure.koshova.finalProject.db.dao;

import ua.nure.koshova.finalProject.db.entity.Role;
import ua.nure.koshova.finalProject.db.entity.User;

import java.util.List;

public interface UserDao {

    /**
     * Select all users
     *
     * @return {@link List<User>}
     */

    List<User> findAllUsers();

    /**
     * Select user by login and password
     *
     * @param login
     * @param password
     * @return {@link User}
     */

    User findUser(String login, String password);

    /**
     * Select user by id
     *
     * @param id
     * @return {@link User}
     */
    User findUserById(Long id);

    /**
     * Select user by login
     *
     * @param login
     * @return {@link User}
     */

    User findUserByLogin(String login);

    /**
     * Create a new user
     *
     * @param user
     * @return id user
     */

    Long createUser(User user);

    /**
     * Delete user
     *
     * @param id
     */

    void deleteUser(Long id);

    /**
     * Update user passport details
     *
     * @param thirdName
     * @param passSeria
     * @param dataPass
     * @param id
     */

    void updateUser(String thirdName,
                    String passSeria,
                    String dataPass,
                    Long id);

    /**
     * Update user role
     *
     * @param id
     */
    void updateUserRole(Long id);

    /**
     * Update status user on blocked
     *
     * @param id
     */

    void updateBlockUser(Long id);

    /**
     * Update status user on onblocked
     *
     * @param id
     */
    void updateUnblockUser(Long id);

    /**
     * Select user by role
     *
     * @param id
     * @return {@link Role}
     */
    Role findRoleByUser(Long id);

    /**
     * User by passport serial number
     *
     * @param seria
     * @return
     */
    User findUserByPassportSeria(String seria);
}
