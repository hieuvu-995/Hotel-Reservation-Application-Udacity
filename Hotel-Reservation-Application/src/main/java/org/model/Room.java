package org.model;

import org.enums.RoomType;

public class Room implements IRoom{

    public Double getPrice() {
        return price;
    }

    private String roomNumber;
    private Double price;

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    private RoomType roomType;

    public Room(){}
    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return price == 0;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber +
                "\nPrice: " + price +
                "\nRoom Type: " + roomType;
    }
}
