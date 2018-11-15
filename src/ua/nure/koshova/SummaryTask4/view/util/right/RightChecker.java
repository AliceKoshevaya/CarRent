package ua.nure.koshova.SummaryTask4.view.util.right;

import ua.nure.koshova.SummaryTask4.db.entity.Role;
import ua.nure.koshova.SummaryTask4.db.entity.Roles;
import ua.nure.koshova.SummaryTask4.db.entity.User;

import javax.servlet.http.HttpSession;

/**
 *Class that checks user access to a resource by role in the system
 *
 */

public class RightChecker {

    public static final String ATTRIBUTE_USER = "user";

    private static boolean roleChecker(HttpSession session, Roles roles) {
        Object userObject = session.getAttribute(ATTRIBUTE_USER);
        if (userObject == null) {
            return false;
        }
        if (userObject instanceof User) {
            User user = (User) userObject;
            Role role  = user.getRole();
            if (role == null || role.getName() == null) {
                return false;
            }
            if (Roles.valueOf(role.getName()) == roles) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public static boolean isUser(HttpSession session) {
        return roleChecker(session,Roles.user);
    }

    public static boolean isManager(HttpSession session) {
      return roleChecker(session,Roles.manager);
    }

    public static boolean isAdmin(HttpSession session) {
        return roleChecker(session,Roles.administrator);
    }
}
