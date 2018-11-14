package ua.nure.koshova.finalProject.view.util.validator.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldValidatorUtil {

    public static final String MESSAGE_VALID = "";

    private static final String MESSAGE_EMPTY_LOGIN = "Login must be not empty";
    private static final String MESSAGE_EMPTY_PASSWORD = "Password must be not empty";
    private static final String MESSAGE_EMPTY_NAME = "Field name must be not empty";
    private static final String MESSAGE_EMPTY_LAST_NAME = "Field lastName must be not empty";
    private static final String MESSAGE_EMPTY_THIRD_NAME = "Third name must be not empty";
    private static final String MESSAGE_EMPTY_SERIES = "Passport series must be not empty";
    private static final String MESSAGE_EMPTY_ISSUED = "Passport data must be not empty";

    private static final String MESSAGE_NOT_VALID_LOGIN = "Login contains invalid characters";
    private static final String MESSAGE_NOT_VALID_PASSWORD = "Password contains invalid characters";
    private static final String MESSAGE_NOT_VALID_NAME = "Field name contains invalid characters";
    private static final String MESSAGE_NOT_VALID_LAST_NAME = "Field Last Name contains invalid characters";
    private static final String MESSAGE_NOT_VALID_THIRD_NAME = "Field third name contains invalid characters";
    private static final String MESSAGE_NOT_VALID_SERIES = "Field passport series contains invalid characters";
    private static final String MESSAGE_NOT_VALID_PASSPORT_DATA = "Field passport data contains invalid characters";

    private static final String REGEXP_NAMES = "^[A-Za-zА-Яа-я]+";
    private static final String REGEXP_LOGIN = "^[\\w.-]{0,19}[0-9a-zA-Z]$";
    private static final String REGEXP_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
    private static final String REGEXP_THIRD_NAME = "[A-ZА-Я][a-zа-я]+";
    private static final String REGEXP_SERIES = "[А-Я]{2}[0-9]{6}";
    private static final String REGEXP_PASSPORT_CITY = "[A-ZА-Я][a-zа-я]+";


    public static String validateLogin(String login) {
        return validateField(login, MESSAGE_EMPTY_LOGIN, REGEXP_LOGIN, MESSAGE_NOT_VALID_LOGIN);
    }

    public static String validatePassword(String password) {
        return validateField(password, MESSAGE_EMPTY_PASSWORD, REGEXP_PASSWORD, MESSAGE_NOT_VALID_PASSWORD);
    }

    public static String validateName(String name) {
        return validateField(name, MESSAGE_EMPTY_NAME, REGEXP_NAMES, MESSAGE_NOT_VALID_NAME);
    }

    public static String validateLastName(String lastName) {
        return validateField(lastName, MESSAGE_EMPTY_LAST_NAME, REGEXP_NAMES, MESSAGE_NOT_VALID_LAST_NAME);
    }

    public static String validateThirdName(String thirdName){
        return validateField(thirdName, MESSAGE_EMPTY_THIRD_NAME, REGEXP_THIRD_NAME, MESSAGE_NOT_VALID_THIRD_NAME);
    }

    public static String validateSeries(String series){
        return validateField(series, MESSAGE_EMPTY_SERIES, REGEXP_SERIES, MESSAGE_NOT_VALID_SERIES);
    }

    public static String validateDataPass(String dataPass){
        return validateField(dataPass, MESSAGE_EMPTY_ISSUED, REGEXP_PASSPORT_CITY, MESSAGE_NOT_VALID_PASSPORT_DATA);
    }

    private static String validateField(String field,
                                        String errorMessageEmpty,
                                        String regexp,
                                        String errorMessageInvalidChars) {
        if (field == null || field.isEmpty()) {
            return errorMessageEmpty;
        }
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(field);
        boolean regexpResult = matcher.matches();
        if (!regexpResult) {
            return errorMessageInvalidChars;
        }
        return MESSAGE_VALID;
    }
}
