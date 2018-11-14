package ua.nure.koshova.finalProject.view.util.validator;

import ua.nure.koshova.finalProject.db.entity.User;
import ua.nure.koshova.finalProject.view.util.validator.util.FieldValidatorUtil;

public class RegistrationValidator {

    private static final String MESSAGE_LOGIN_EXIST = "Login is already in use";

    public static String validate(String login, User existingUser, String password, String name, String lastName) {

        if (existingUser != null) {
            return MESSAGE_LOGIN_EXIST;
        }

        String loginErrorMessage = FieldValidatorUtil.validateLogin(login);
        if (!FieldValidatorUtil.MESSAGE_VALID.equals(loginErrorMessage)) {
            return loginErrorMessage;
        }

        String passwordErrorMessage = FieldValidatorUtil.validatePassword(password);
        if (!FieldValidatorUtil.MESSAGE_VALID.equals(passwordErrorMessage)) {
            return passwordErrorMessage;
        }

        String nameErrorMessage = FieldValidatorUtil.validateName(name);
        if (!FieldValidatorUtil.MESSAGE_VALID.equals(name)) {
            return nameErrorMessage;
        }

        String lastNameErrorMessage = FieldValidatorUtil.validateLastName(lastName);
        if (!FieldValidatorUtil.MESSAGE_VALID.equals(lastNameErrorMessage)) {
            return lastNameErrorMessage;
        }

        return FieldValidatorUtil.MESSAGE_VALID;
    }
}
