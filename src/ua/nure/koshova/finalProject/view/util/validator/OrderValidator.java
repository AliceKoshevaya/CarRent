package ua.nure.koshova.finalProject.view.util.validator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderValidator {
    private static final String MESSAGE_EMPTY_DRIVER = "Driver option not filled";
    private static final String MESSAGE_EMPTY_START_RENT = "Start data must be not empty";
    private static final String MESSAGE_EMPTY_END_RENT = "End data must be not empty";
    private static final String MESSAGE_EMPTY_THIRD_NAME = "Third name must be not empty";
    private static final String MESSAGE_EMPTY_SERIES = "Passport series must be not empty";
    private static final String MESSAGE_EMPTY_ISSUED = "Passport data must be not empty";
    private static final String MESSAGE_VALID = "";
    private static final String MESSAGE_NOT_VALID_DATA = "Date already passed";
    private static final String MESSAGE_NOT_VALID_THIRD_NAME = "Field third name contains invalid characters";
    private static final String MESSAGE_NOT_VALID_SERIES = "Field passport series contains invalid characters";
    private static final String MESSAGE_NOT_VALID_PASSPORT_DATA = "Field passport data contains invalid characters";
    private static final String REGEXP_THIRD_NAME = "/^([A-Za-z]+|[А-Яа-я]+)$/u";
    private static final String REGEXP_SERIES = "^[А-Я]{2}\\d{6}$";
    private static final String REGEXP_PASSPORT_DATA = "/^([A-Za-z]+|[А-Яа-я]+)$/u";


    public static String validate(String driver, String startRent, String endRent, String thirdName,
                                  String series, String issued) {
        if (driver == null || driver.isEmpty()) {
            return MESSAGE_EMPTY_DRIVER;
        }
        if (startRent == null || startRent.isEmpty()) {
            return MESSAGE_EMPTY_START_RENT;
        }
        if (endRent == null || endRent.isEmpty()) {
            return MESSAGE_EMPTY_END_RENT;
        }
        if (thirdName == null || thirdName.isEmpty()) {
            return MESSAGE_EMPTY_THIRD_NAME;
        }
        if (series == null || series.isEmpty()) {
            return MESSAGE_EMPTY_SERIES;
        }
        if (issued == null || issued.isEmpty()) {
            return MESSAGE_EMPTY_ISSUED;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        if((startRent.compareTo(timeStamp) == 1) && (endRent.compareTo(thirdName) ==1 )){
            return MESSAGE_NOT_VALID_DATA;
        }
        Pattern patternThirdName = Pattern.compile(REGEXP_THIRD_NAME);
        Matcher matcherThirdName = patternThirdName.matcher(thirdName);
        boolean foundThirdName = matcherThirdName.matches();
        if(!foundThirdName) {
            return MESSAGE_NOT_VALID_THIRD_NAME;
        }
        Pattern patternSeries = Pattern.compile(REGEXP_SERIES);
        Matcher matcherSeries = patternSeries.matcher(thirdName);
        boolean foundSeries = matcherSeries.matches();
        if(!foundSeries) {
            return MESSAGE_NOT_VALID_SERIES;
        }
        Pattern patternData = Pattern.compile(REGEXP_PASSPORT_DATA);
        Matcher matcherData = patternData.matcher(thirdName);
        boolean foundData = matcherData.matches();
        if(!foundData) {
            return MESSAGE_NOT_VALID_PASSPORT_DATA;
        }
        return MESSAGE_VALID;
    }
}
