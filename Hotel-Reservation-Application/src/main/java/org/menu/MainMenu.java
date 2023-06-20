package org.menu;


import org.api.HotelResource;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class MainMenu {
    private HotelResource hotelResource;
    private Scanner scanner;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public MainMenu(HotelResource hotelResource) {
        this.hotelResource = hotelResource;
    }
    public MainMenu() {}

    public void printMenuItems() {
        System.out.println("""
                ======== MAIN MENU ========
                1. Find and reserve a room
                2. See my reservations
                3. Create an account
                4. Admin
                5. Exit
                """);
    }

    public void start() {
        scanner = new Scanner(System.in);
        while (true) {
            printMenuItems();
            var c = scanner.nextLine();
            switch (c) {
                case "1":
                    try {
                        findAndReserveARoom();
                    } catch (ParseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    seeMyReservations();
                    break;
                case "3":
                    createAccount();
                    break;
                case "4":
                    openAdminMenu();
                    break;
                default:
                    return;
            }
        }
    }

    void findAndReserveARoom() throws ParseException {
        System.out.println("Check-in date (yyyy/MM/dd): ");
        var checkIn = sdf.parse(scanner.nextLine());
        System.out.println("Check-out date (yyyy/MM/dd): ");
        var checkOut = sdf.parse(scanner.nextLine());

        var availableRooms = hotelResource.findARoom((Date) checkIn, (Date) checkOut);
        if (availableRooms.isEmpty()) {
            var recommendedRooms = hotelResource.getRecommendedRooms((Date) checkIn, (Date) checkOut);
            if (recommendedRooms.isEmpty()) {
                System.out.println("Does not find any available rooms");
                return;
            }
            System.out.println("Does not find any available rooms depend on your expected check-in and check-out date.");
            System.out.println("Please book room for the alternative dates.");
            System.out.println("Recommended rooms: (will be available after 7 days)");
            recommendedRooms.forEach(System.out::println);
            return;
        }
        System.out.println("Available rooms:");
        availableRooms.forEach(System.out::println);

        System.out.println("Select room number: ");
        var room = hotelResource.getRoom(scanner.nextLine());
        if (room == null) {
            System.out.println("Room number does not exist");
            return;
        }
        System.out.println("Enter your email: eg. name@domain.com");
        var email = scanner.nextLine();
        try {
            hotelResource.bookARoom(email, room, (Date) checkIn, (Date) checkOut);
            System.out.println("Booking is successful");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void seeMyReservations() {
        System.out.println("Email: ");
        var email = scanner.nextLine();
        hotelResource.getCustomerReservations(email).forEach(System.out::println);
    }

    void createAccount() {
        System.out.println("First name: ");
        var fn = scanner.nextLine();
        System.out.println("Last name: ");
        var ln = scanner.nextLine();
        System.out.println("Enter your email: eg. name@domain.com");
        var email = scanner.nextLine();
        try {
            hotelResource.createACustomer(email, fn, ln);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    void openAdminMenu() {
        (new AdminMenu()).start();
    }
}