package org.service;

import org.model.Customer;
import org.model.IRoom;
import org.model.Reservation;
import org.model.Room;

import java.util.Date;
import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {

    private static ReservationService reservationService;
    private static final Collection<IRoom> rooms = new ArrayList<IRoom>();
    private static final Collection<Reservation> reservations = new ArrayList<>();

    public static ReservationService getReservationService() {
        if (reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom(IRoom room) {
        rooms.add(room);
    }

    public IRoom getARoom(String roomId) {
        return rooms.stream()
                .filter(r -> r.getRoomNumber().equals(roomId))
                .findFirst()
                .orElse(null);
    }

    public Reservation reserveRoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }


    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Set<String> occupiedRoomNumbers = reservations.stream()
                .filter(r -> r.getCheckInDate().equals(checkInDate) && r.getCheckOutDate().equals(checkOutDate))
                .map(Reservation::getIRoom)
                .map(IRoom::getRoomNumber)
                .collect(Collectors.toSet());

        return rooms.stream()
                .filter(r -> !occupiedRoomNumbers.contains(r.getRoomNumber()))
                .collect(Collectors.toList());
    }

    public boolean checkDuplicateReservation(Date checkInDate, Date checkOutDate, Customer customer) {
        return reservations.stream()
                .anyMatch(r -> r.getCheckInDate().equals(checkInDate) && r.getCheckOutDate().equals(checkOutDate) && r.getCustomer().equals(customer));
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> customerReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }

    public void printAllReservations() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public static Collection<IRoom> getRooms() {
        return Collections.unmodifiableCollection(rooms);
    }
    public Collection<IRoom> getRecommendedRooms(Date originCheckIn, Date originCheckOut) {
        var c = Calendar.getInstance();
        c.setTime(originCheckIn);
        c.add(Calendar.DATE, 7);
        var checkIn = c.getTime();
        c.setTime(originCheckOut);
        c.add(Calendar.DATE, 7);
        var checkOut = c.getTime();
        return findRooms(checkIn,checkOut);
    }

    public boolean checkRoomNumCorrect(Collection<IRoom> availableRooms, String roomNum) {
        return availableRooms.stream().anyMatch(r -> r.getRoomNumber().equals(roomNum));
    }
}
