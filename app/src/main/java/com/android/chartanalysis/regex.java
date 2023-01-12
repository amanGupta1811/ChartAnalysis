package com.android.chartanalysis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regex {
        public static boolean isValidPassword(final String password) {

                Pattern pattern;
                Matcher matcher;
                final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
                pattern = Pattern.compile(PASSWORD_PATTERN);
                matcher = pattern.matcher(password);

                return matcher.matches();
        }
}