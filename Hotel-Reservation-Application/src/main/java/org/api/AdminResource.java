package org.api;

import org.model.Customer;
import org.model.IRoom;
import org.service.CustomerService;
import org.service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {

    private static AdminResource adminResource;
    private final CustomerService customerService;
    private final ReservationService reservationService;
    private AdminResource(CustomerService customerService, ReservationService reservationService) {
        this.customerService = customerService;
        this.reservationService = reservationService;
    }

    public static AdminResource getAdminResource() {
        if (adminResource == null) {
            adminResource = new AdminResource(CustomerService.getCustomerService(), ReservationService.getReservationService());
        }
        return adminResource;
    }
    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {
        rooms.forEach(reservationService::addRoom);
    }

    public Collection<IRoom> getAllRooms() {
        return ReservationService.getRooms();
    }

    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationService.printAllReservations();
    }
}