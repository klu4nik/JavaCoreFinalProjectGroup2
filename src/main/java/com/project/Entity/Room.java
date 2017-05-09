package Entity;

/**
 * @version final :)
 *          Entity-Class. Describes the entity of ROOM.
 *          Have next fields:
 *          hotel - Hotel where the Room is located;
 * @see Hotel
 * roomNumber - unique Number of room in Hotel ;
 * numberOfPerson - quantity of bed;
 * price - Value of price per day;
 */

public class Room {
    private Integer roomNumber;
    private Hotel hotel;
    private Integer numberOfPerson;
    private Integer price;

    public Room(Hotel hotel, Integer roomNumber, Integer numberOfPerson, Integer price) {
        this.hotel = hotel;
        this.numberOfPerson = numberOfPerson;
        this.price = price;
        this.roomNumber = roomNumber;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Integer getNumberOfPerson() {
        return numberOfPerson;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (!roomNumber.equals(room.roomNumber)) return false;
        if (!hotel.equals(room.hotel)) return false;
        if (!numberOfPerson.equals(room.numberOfPerson)) return false;
        return price.equals(room.price);
    }

    @Override// автомотчески сгенерировался код повторялся, для разных комнат!!!!!  (пришлось править)
    public int hashCode() {
        int result = roomNumber.hashCode();
        result = 71 * result + hotel.hashCode();
        result = 31 * result + numberOfPerson.hashCode();
        result = 59 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Number: " + roomNumber +
                ", Hotel: " + hotel.getHotelName() +
                ", Number Of Person: " + numberOfPerson +
                ", Price: " + price + "\n";
    }
}
