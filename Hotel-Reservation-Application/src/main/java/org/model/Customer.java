package org.model;

import java.util.regex.Pattern;

public class Customer {

    public String getFirstName() {
        return firstName;
    }

    private String firstName;
    private String lastName;
    private String email;

    public Customer(String firstName, String lastName, String email) {
        if (!isValidEmail(email)) {
        throw new IllegalArgumentException("Invalid email");}
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private boolean isValidEmail(String email) {
        String emailValid = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailValid, email);
    }

    @Override
    public String toString() {
        return "Customer{" +
            "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", email='" + email + '\'' +
                    '}';
    }
}
