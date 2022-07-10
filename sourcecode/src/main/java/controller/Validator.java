package controller;

import common.exception.InvalidDeliveryInfoException;

import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String PATTERN_STRING_FOR_NAME_AND_ADDRESS = "^[a-zA-Z\\s]*$";

    public static void validateDeliveryInfo(HashMap<String, String> info) throws InvalidDeliveryInfoException {
        if (validatePhoneNumber(info.get("phone"))
                || validateName(info.get("name"))
                || validateAddress(info.get("address"))) {
            return;
        }
        else {
            throw new InvalidDeliveryInfoException();
        }
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 10) {
            return false;
        }
        if (!phoneNumber.startsWith("0")) {
            return false;
        }
        try {
            Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean validateName(String name) {
        if (Objects.isNull(name)) {
            return false;
        }
        Pattern pattern = Pattern.compile(PATTERN_STRING_FOR_NAME_AND_ADDRESS);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean validateAddress(String address) {
        if (Objects.isNull(address)) {
            return false;
        }
        Pattern pattern = Pattern.compile(PATTERN_STRING_FOR_NAME_AND_ADDRESS);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }

}
