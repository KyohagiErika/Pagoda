package com.processor.applicaion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validater {

    public static boolean validate(String input, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        return m.find();
    }

}
