package com.abdirahman.kmwallet.validation;

import com.abdirahman.kmwallet.model.entity.Customer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerValidation {
    public static void validateCustomer(Customer customer) {
        if (customer.getFirstName().length() < 5) throw new RuntimeException("first name too short");
        if (customer.getLastName().length() < 5) throw new RuntimeException("last name too short");
        if (!isValidEmail(customer.getEmail())) throw new RuntimeException("not a valid email");
        if (!isValidPhoneNumber(customer.getPhoneNumber())) throw new RuntimeException("not a valid phone number");
        if (customer.getPassword().length() < 5) throw new RuntimeException("password too short");
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^\\+\\d{3}-\\d{3}-\\d{3}-\\d{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
