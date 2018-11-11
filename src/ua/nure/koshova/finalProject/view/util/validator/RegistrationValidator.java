package ua.nure.koshova.finalProject.view.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationValidator {

    private static final String MESSAGE_EMPTY_NAME = "Field name must be not empty";
    private static final String MESSAGE_EMPTY_LAST_NAME = "Field lastName must be not empty";
    private static final String MESSAGE_NOT_VALID_NAME = "Field name contains invalid characters";
    private static final String MESSAGE_NOT_VALID_LAST_NAME = "Field lastname contains invalid characters";
    private static final String MESSAGE_VALID = "";
    private static final String REGEXP_VALID = "/^[A-Za-zА-Яа-я]+$/u";

    public static String validate(String login, String password, String name, String lastName) {
        LoginValidator.validate(login,password);
        if (name == null || name.isEmpty()) {
            return MESSAGE_EMPTY_NAME;
        }
        if (lastName == null || lastName.isEmpty()) {
            return MESSAGE_EMPTY_LAST_NAME;
        }
        Pattern patternName = Pattern.compile(REGEXP_VALID);
        Matcher matcherName = patternName.matcher(name);
        boolean foundName = matcherName.matches();
        if(!foundName) {
            return MESSAGE_NOT_VALID_NAME;
        }
        Pattern patternLastName = Pattern.compile(REGEXP_VALID);
        Matcher matcherLastName = patternLastName.matcher(name);
        boolean foundLastName = matcherLastName.matches();
        if(!foundLastName) {
            return MESSAGE_NOT_VALID_LAST_NAME;
        }

        return MESSAGE_VALID;
    }
}
