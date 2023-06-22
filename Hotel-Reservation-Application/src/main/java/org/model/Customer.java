package org.model;

import java.util.regex.Pattern;

public class Customer {

    public String getFirstName() {
        return firstName;
    }

    private String firstName;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String lastName;
    private String email;

    public Customer(String email, String firstName, String lastName) {
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
