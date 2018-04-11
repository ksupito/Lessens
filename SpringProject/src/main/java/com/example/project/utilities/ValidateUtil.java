package com.example.project.utilities;

import java.io.IOException;

public class ValidateUtil {
    private ValidateUtil() {
    }

    public static String validate(String name) throws IOException {
        String lastName = new String(name.getBytes("ISO-8859-1"), "UTF-8");
        if (lastName == null || lastName == "" || lastName.isEmpty()) {
            return null;
        }
        return lastName;
    }
}
