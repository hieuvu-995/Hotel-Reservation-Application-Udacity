package org.service;

import org.model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private static Map<String, Customer> customers = new HashMap<>();

    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(email, firstName, lastName);
        customers.put(email, customer);
    }

    public Customer getCustomer(String customerEmail) {
        return customers.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }

    public static Map<String, Customer> getCustomers() {
        return customers;
    }
}
