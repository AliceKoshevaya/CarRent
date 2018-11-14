package ua.nure.koshova.finalProject.view.util.validator;

import ua.nure.koshova.finalProject.db.entity.User;
import ua.nure.koshova.finalProject.view.util.validator.util.FieldValidatorUtil;

/**
 * Class for validating user authorization
 */
public class AuthorizationValidator {

    private static final String MESSAGE_VALID_LOGIN_NOT_EXIST = "This user not exists";

    public static String validate(String login, User existingUser, String password) {
        if (existingUser == null) {
            return MESSAGE_VALID_LOGIN_NOT_EXIST;
        }

        String loginErrorMessage = FieldValidatorUtil.validateLogin(login);
        if (!FieldValidatorUtil.MESSAGE_VALID.equals(loginErrorMessage)) {
            return loginErrorMessage;
        }

        String passwordErrorMessage = FieldValidatorUtil.validatePassword(password);
        if (!FieldValidatorUtil.MESSAGE_VALID.equals(passwordErrorMessage)) {
            return passwordErrorMessage;
        }

        return FieldValidatorUtil.MESSAGE_VALID;
    }
}
