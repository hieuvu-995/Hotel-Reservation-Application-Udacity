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

    private Customer customer;
    private IRoom iRoom;
    private Date checkInDate;
    private Date checkOutDate;

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
