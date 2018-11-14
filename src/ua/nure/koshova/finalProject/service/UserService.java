package ua.nure.koshova.finalProject.service;

import ua.nure.koshova.finalProject.db.entity.Role;
import ua.nure.koshova.finalProject.db.entity.User;

import java.util.List;

/**
 * Interface for common user actions
 */
public interface UserService {

    /**
     * Create a new user
     *
     * @param login
     * @param password
     * @param name
     * @param lastName
     * @param thirdName
     * @return {@Link User}
     */
    public User createNewUser(String login,
                              String password,
                              String name,
                              String lastName,
                              String thirdName);

    /**
     *
     * Authorization check
     *
     * @param login
     * @param password
     * @return {@Link User}
     */
    public User registeredUser(String login, String password);

    /**
     * Select all users
     *
     * @return {@Link List<User>}
     */
    public List<User> getAllUsers();

    /**
     * Add additional info user
     *
     * @param id
     * @param thirdName
     * @param passSer
     * @param passInfo
     */
    public void addUserInfo(Long id, String thirdName, String passSer, String passInfo);

    /**
     * Assign user to manager
     *
     * @param id user
     */

    public void makeManager(Long id);

    /**
     * Select user by login
     *
     * @param login
     * @return {@link User}
     */

    public User getUserByLogin(String login);

    /**
     * Select user role by id
     *
     * @param id
     * @return {@Link Role}
     */

    public Role getUserRole(Long id);

    /**
     * Change user status to blocked
     *
     * @param id
     */

    public void blockUser(Long id);

    /**
     * Change user status to unblocked
     *
     * @param id
     */
    public void unblockUser(Long id);

    /**
     * Find all users with blocked status
     *
     * @param id
     * @return {@Link User}
     */
    public User checkBlockUser(Long id);

    /**
     * Check whether there are such passport data in the system
     *
     * @return existSeries
     */

    public boolean passportSeriaExist();

}
