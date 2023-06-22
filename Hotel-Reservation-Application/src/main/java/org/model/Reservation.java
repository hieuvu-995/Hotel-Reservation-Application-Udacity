package org.model;

import java.util.Date;

public class Reservation {
    public Customer getCustomer() {
        return customer;
    }

    public IRoom getIRoom() {
        return iRoom;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    private final Customer customer;
    private final IRoom iRoom;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservation(Customer customer, IRoom iRoom, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.iRoom = iRoom;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation Details:\n" +
                "Customer: " + customer +
                "Room: " + iRoom +
                "Check-in Date: " + checkInDate +
                "Check-out Date: " + checkOutDate;
    }
}
