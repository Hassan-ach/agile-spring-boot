package com.ensa.agile.application.common.utils;

import java.util.regex.Pattern;

public class ValidationUtil {
    public static boolean patternMatches(String emailAddress,
                                         String regexPattern) {
        return Pattern.compile(regexPattern).matcher(emailAddress).matches();
    }

    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        return patternMatches(email, EMAIL_PATTERN);
    }
}
