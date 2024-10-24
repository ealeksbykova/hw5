package org.example;

import java.util.List;

public class Utils {
    public static final List<String> OPERATORS = List.of("+", "-", "*", "/", "sqr", "sin", "cos", "pow");


    public static boolean isNumber(String number) {
        try {
            Integer.valueOf(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
