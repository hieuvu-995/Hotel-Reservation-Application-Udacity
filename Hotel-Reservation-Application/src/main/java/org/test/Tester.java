package org.test;

import org.model.Customer;

public class Tester {
    public static void main(String[] args) {
        Customer customer = new Customer("first", "second", "j@domain.com");
        System.out.println(customer);
        customer = new Customer("first", "second", "email");
    }
}
