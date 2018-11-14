package ua.nure.koshova.finalProject.view.util.validator;

import ua.nure.koshova.finalProject.db.entity.User;
import ua.nure.koshova.finalProject.view.util.validator.util.FieldValidatorUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class for validating order creation
 */
public class OrderValidator {
    private static final String MESSAGE_EMPTY_DRIVER = "Driver option not filled";
    private static final String MESSAGE_EMPTY_START_RENT = "Start data must be not empty";
    private static final String MESSAGE_EMPTY_END_RENT = "End data must be not empty";

    private static final String MESSAGE_VALID = "";
    private static final String MESSAGE_NOT_VALID_DATA = "Date already passed";
    public static final String MESSAGE_NOT_VALID_SERIES = "Passport series is already in use";

    public static String validate(String driver,
                                  String startRent,
                                  String endRent,
                                  String thirdName,
                                  String series,
                                  String issued,
                                  User userWithPassportSeria) {

        if (userWithPassportSeria != null) {
            return MESSAGE_NOT_VALID_SERIES;
        }
        if (driver == null || driver.isEmpty()) {
            return MESSAGE_EMPTY_DRIVER;
        }
        if (startRent == null || startRent.isEmpty()) {
            return MESSAGE_EMPTY_START_RENT;
        }
        if (endRent == null || endRent.isEmpty()) {
            return MESSAGE_EMPTY_END_RENT;
        }
        String thirdNameErrorMessage = FieldValidatorUtil.validateThirdName(thirdName);
        if (!FieldValidatorUtil.MESSAGE_VALID.equals(thirdNameErrorMessage)) {
            return thirdNameErrorMessage;
        }
        String timeStamp = new SimpleDateFormat("yyyy-MM-ddТHH-mm").format(Calendar.getInstance().getTime());
        if ((timeStamp.compareTo(startRent) > 0) && (timeStamp.compareTo(endRent) > 0)
                && (endRent.compareTo(startRent) > 0)) {
            return MESSAGE_NOT_VALID_DATA;
        }
        String seriesErrorMessage = FieldValidatorUtil.validateSeries(series);
        if (!FieldValidatorUtil.MESSAGE_VALID.equals(seriesErrorMessage)) {
            return seriesErrorMessage;
        }
        String issuedErrorMessage = FieldValidatorUtil.validateThirdName(issued);
        if (!FieldValidatorUtil.MESSAGE_VALID.equals(issuedErrorMessage)) {
            return issuedErrorMessage;
        }
        return MESSAGE_VALID;
    }
}
