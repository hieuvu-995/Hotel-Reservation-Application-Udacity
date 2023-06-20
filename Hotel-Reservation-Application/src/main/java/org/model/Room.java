package org.model;

import org.enums.RoomType;

public class Room implements IRoom{

    private String roomNumber;
    private Double price;
    private RoomType roomType;
    @Override
    public String getRoomNumber() {
        return null;
    }

    @Override
    public Double getRoomPrice() {
        return null;
    }

    @Override
    public RoomType getRoomType() {
        return null;
    }

    @Override
    public boolean isFree() {
        return false;
    }
}
