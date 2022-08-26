package com.example.demo.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidation implements Predicate<String> {

    String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

    /*
    * Checks if the email is valid:
    *    A-Z characters allowed
    *    a-z characters allowed
    *    0-9 numbers allowed
    *    Additionally email may contain only dot(.), dash(-) and underscore(_)
    *    Rest all characters are not allowed
    * */
    @Override
    public boolean test(String email) {
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(email);
        return match.matches();
    }
}
