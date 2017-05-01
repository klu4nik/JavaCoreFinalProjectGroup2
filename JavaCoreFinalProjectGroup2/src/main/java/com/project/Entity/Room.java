package com.project.Entity;

import com.project.Entity.Hotel;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by MYKOLA.GOROKHOV on 21.04.2017.
 */
public class Room implements Serializable {
    private Integer roomNumber;
    private Hotel hotel;
    private Integer numberOfperson;
    private Integer price;
    private User reservedForUser;
    private Date dateOfBookingStart;
    private Date dateOfBookingFinish;

    public Room(Hotel hotel, Integer roomNumber, Integer numberOfPerson, Integer price) {
        this.hotel = hotel;
        this.numberOfperson = numberOfPerson;
        this.price = price;
        this.roomNumber = roomNumber;
        this.reservedForUser = null;
        this.dateOfBookingStart = new Date(0);
        this.dateOfBookingFinish = new Date(0);

    }

    public Room(Hotel hotel, Integer roomNumber, Integer numberOfperson, Integer price, User reservedForUser, Date dateOfBookingStart, Date dateOfBookingFinish) {
        this.hotel = hotel;
        this.numberOfperson = numberOfperson;
        this.price = price;
        this.reservedForUser = reservedForUser;
        this.dateOfBookingStart = dateOfBookingStart;
        this.dateOfBookingFinish = dateOfBookingFinish;
        this.roomNumber = roomNumber;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Integer getNumberOfperson() {
        return numberOfperson;
    }

    public Integer getPrice() {
        return price;
    }

    public User getReservedForUser() {
        return reservedForUser;
    }

    public Date getDateOfBookingStart() {
        return dateOfBookingStart;
    }

    public Date getDateOfBookingFinish() {
        return dateOfBookingFinish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (!roomNumber.equals(room.roomNumber)) return false;
        if (!hotel.equals(room.hotel)) return false;
        if (!numberOfperson.equals(room.numberOfperson)) return false;
        return price.equals(room.price);
    }

    @Override// автомотчески сгенерировался код повторялся, для разных комнат!!!!!  (пришлось править)
    public int hashCode() {
        int result = roomNumber.hashCode();
        result = 71 * result + hotel.hashCode();
        result = 31 * result + numberOfperson.hashCode();
        result = 59 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        if (reservedForUser != null) {
            return "Room{" +
                    "roomNumber=" + roomNumber +
                    ", hotel=" + hotel +
                    ", numberOfperson=" + numberOfperson +
                    ", price=" + price +
                    ", reservedForUser=" + reservedForUser +
                    ", dateOfBookingStart=" + dateOfBookingStart +
                    ", dateOfBookingFinish=" + dateOfBookingFinish +
                    '}' + "\n";
        }
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", hotel=" + hotel +
                ", numberOfperson=" + numberOfperson +
                ", price=" + price + ", room NOT reserwved" +

                '}' + "\n";
    }
}
