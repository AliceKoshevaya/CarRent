package ua.nure.koshova.finalProject.view.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginValidator {

    private static final String MESSAGE_EMPTY_LOGIN = "Login must be not empty";
    private static final String MESSAGE_EMPTY_PASSWORD = "Password must be not empty";
    private static final String MESSAGE_NOT_VALID_LOGIN = "Login contains invalid characters";
    private static final String MESSAGE_NOT_VALID_PASSWORD = "Password contains invalid characters";
    private static final String MESSAGE_VALID = "";
    private static final String REGEXP_LOGIN = "^[\\w.-]{0,19}[0-9a-zA-Z]$";
    private static final String REGEXP_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";


    public static String validate(String login, String password) {
        if (login == null || login.isEmpty()) {
            return MESSAGE_EMPTY_LOGIN;
        }
        if (password == null || password.isEmpty()) {
            return MESSAGE_EMPTY_PASSWORD;
        }
        Pattern patternLogin = Pattern.compile(REGEXP_LOGIN);
        Matcher matcherLogin = patternLogin.matcher(login);
        boolean foundLogin = matcherLogin.matches();
        if (!foundLogin) {
            return MESSAGE_NOT_VALID_LOGIN;
        }
        Pattern patternPassword = Pattern.compile(REGEXP_PASSWORD);
        Matcher matcherPassword = patternPassword.matcher(password);
        boolean foundPassword = matcherPassword.matches();
        if (!foundPassword) {
            return MESSAGE_NOT_VALID_PASSWORD;
        }
        return MESSAGE_VALID;
    }
}
