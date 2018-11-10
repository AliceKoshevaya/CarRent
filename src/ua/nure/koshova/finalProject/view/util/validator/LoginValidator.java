package ua.nure.koshova.finalProject.view.util.validator;

public class LoginValidator {


    public static final String MESSAGE_EMPTY_LOGIN = "Login must be not empty";
    public static final String MESSAGE_EMPTY_PASSWORD = "Password must be not empty";
    public static final String MESSAGE_VALID = "";

    public static String validate(String login, String password) {
        if (login == null || login.isEmpty()) {
            return MESSAGE_EMPTY_LOGIN;
        }
        if (password == null || password.isEmpty()) {
            return MESSAGE_EMPTY_PASSWORD;
        }
        // todo regexp them
        return MESSAGE_VALID;
    }
}
