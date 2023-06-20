package org.api;

import org.model.Customer;
import org.model.IRoom;
import org.model.Reservation;
import org.service.CustomerService;
import org.service.ReservationService;

import java.util.Date;
import java.util.Collection;

public class HotelResource {

    private final CustomerService customerService;
    private final ReservationService reservationService;

    private HotelResource(CustomerService customerService, ReservationService reservationService) {
        this.customerService = customerService;
        this.reservationService = reservationService;
    }

    public Customer getCustomer(String customerEmail) {
        return customerService.getCustomer(customerEmail);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String email, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = customerService.getCustomer(email);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }
        return reservationService.reserveRoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        Customer customer = customerService.getCustomer(customerEmail);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }
        return reservationService.getCustomersReservation(customer);
    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        return reservationService.findRooms(checkInDate, checkOutDate);
    }

    public Collection<IRoom> getRecommendedRooms(Date originCheckIn, Date originCheckOut) {
        return reservationService.getRecommendedRooms(originCheckIn, originCheckOut);
    }
}

