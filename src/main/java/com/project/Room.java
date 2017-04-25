package main.java.com.project;

import main.java.com.project.Hotel;
import main.java.com.project.User;

import java.util.Date;

/**
 * Created by MYKOLA.GOROKHOV on 21.04.2017.
 */
public class Room {
    private Integer id;
    private Hotel hotel;
    private Integer numberOfperson;
    private Integer price;
    private User reservedForUser;
    private Date dateOfBookingStart;
    private Date dateOfBookingFinish;

    public Room(Hotel hotel, Integer id, Integer numberOfPerson, Integer price) {
        this.hotel = hotel;
        this.numberOfperson = numberOfPerson;
        this.price = price;
        this.id = id;
        this.reservedForUser = null;
//        this.dateOfBookingStart = dateOfBookingStart;  //
//        this.dateOfBookingFinish = dateOfBookingFinish;//

    }

    public Room(Hotel hotel, Integer id, Integer numberOfperson, Integer price, User reservedForUser, Date dateOfBookingStart, Date dateOfBookingFinish) {
        this.hotel = hotel;
        this.numberOfperson = numberOfperson;
        this.price = price;
        this.reservedForUser = reservedForUser;
        this.dateOfBookingStart = dateOfBookingStart;
        this.dateOfBookingFinish = dateOfBookingFinish;
        this.id = id;
    }

    public Integer getId() {
        return id;
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

        if (!id.equals(room.id)) return false;
        if (!hotel.equals(room.hotel)) return false;
        if (!numberOfperson.equals(room.numberOfperson)) return false;
        return price.equals(room.price);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + hotel.hashCode();
        result = 31 * result + numberOfperson.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        if (reservedForUser != null) {
            return
                    "id=" + id +
                            ", hotel=" + hotel.getHotelName() +
                            ", numberOfperson=" + numberOfperson +
                            ", price=" + price +
                            ", reservedForUser=" + reservedForUser +
                            ", dateOfBookingStart=" + dateOfBookingStart +
                            ", dateOfBookingFinish=" + dateOfBookingFinish;
        }
        return
                "id=" + id +
                        ", hotel=" + hotel.getHotelName() +
                        ", numberOfperson=" + numberOfperson +
                        ", price=" + price + " NOT Reserved";

    }
}
