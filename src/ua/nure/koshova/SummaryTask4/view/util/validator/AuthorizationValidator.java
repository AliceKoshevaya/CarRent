package ua.nure.koshova.SummaryTask4.view.util.validator;

import ua.nure.koshova.SummaryTask4.db.entity.User;
import ua.nure.koshova.SummaryTask4.view.util.validator.util.FieldValidatorUtil;

/**
 * Class for validating user authorization
 */
public class AuthorizationValidator {

    private static final String MESSAGE_USER_NOT_EXIST = "This user not exists";
    private static final String MESSAGE_ACCOUNT_BLOCKED = "Sorry, your account is blocked";

    public static String validate(String login, User existingUser, String password) {
        if (existingUser == null) {
            return MESSAGE_USER_NOT_EXIST;
        }

        if (existingUser.isBlock()) {
            return MESSAGE_ACCOUNT_BLOCKED;
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
