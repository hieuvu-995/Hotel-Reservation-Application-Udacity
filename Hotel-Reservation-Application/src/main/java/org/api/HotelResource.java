package org.api;

import org.model.Customer;
import org.model.IRoom;
import org.model.Reservation;
import org.service.CustomerService;
import org.service.ReservationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;

public class HotelResource {

    private static HotelResource hotelResource;
    private final CustomerService customerService;
    private final ReservationService reservationService;

    private HotelResource(CustomerService customerService, ReservationService reservationService) {
        this.customerService = customerService;
        this.reservationService = reservationService;
    }

    public static HotelResource getHotelResource() {
        if (hotelResource == null) {
            hotelResource = new HotelResource(CustomerService.getCustomerService(), ReservationService.getReservationService());
        }
        return hotelResource;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String email, IRoom room, Date checkInDate, Date checkOutDate) throws Exception {
        Customer customer = customerService.getCustomer(email);
        if (customer == null) {
            throw new Exception("Customer not found! Please create new account before reserve.");
        }
        boolean duplicateReservation = reservationService.checkDuplicateReservation(checkInDate, checkOutDate, customer);
        if (duplicateReservation) {
            throw new Exception("Customer cannot reserve room in the same time");
        }
        return reservationService.reserveRoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.getCustomersReservation(customer);
    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        return reservationService.findRooms(checkInDate, checkOutDate);
    }

    public Collection<IRoom> getRecommendedRooms(Date originCheckIn, Date originCheckOut) {
        return reservationService.getRecommendedRooms(originCheckIn, originCheckOut);
    }

    public boolean getRoomNumCorrect(Collection<IRoom> availableRooms, String roomNum) {
        return reservationService.checkRoomNumCorrect(availableRooms, roomNum);
    }
}

