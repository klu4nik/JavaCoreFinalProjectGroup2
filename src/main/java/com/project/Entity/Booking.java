package Entity;

import java.util.Date;

/**
 * @version final :)
 *          Entity-Class. Describes the entity of Booking.
 *          Have next fields:
 *          id - for identification entry in DB;
 *          user_id - for identification user in DB;
 *          hotel_id - for identification Hotel in DB ;
 *          room_Number - for identification Room in Hotel;
 *          date_start - data of booking start;
 *          date_end- data of booking finish;
 * @see User
 * @see Hotel
 */
public class Booking {
    private Integer id;
    private Integer user_id;
    private Integer room_Number;
    private Integer hotel_id;
    private Date date_start;
    private Date date_end;

    public Booking(Integer user_id, Integer room_Number, Integer hotel_id, Date date_start, Date date_end) {
        this.user_id = user_id;
        this.room_Number = room_Number;
        this.hotel_id = hotel_id;
        this.date_start = date_start;
        this.date_end = date_end;
        this.id = this.hashCode();
    }

    public Booking(Integer id, Integer user_id, Integer room_Number, Integer hotel_id, Date date_start, Date date_end) {
        this.user_id = user_id;
        this.room_Number = room_Number;
        this.hotel_id = hotel_id;
        this.date_start = date_start;
        this.date_end = date_end;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        if (user_id != null ? !user_id.equals(booking.user_id) : booking.user_id != null) return false;
        if (room_Number != null ? !room_Number.equals(booking.room_Number) : booking.room_Number != null) return false;
        if (date_start != null ? !date_start.equals(booking.date_start) : booking.date_start != null) return false;
        return date_end != null ? date_end.equals(booking.date_end) : booking.date_end == null;
    }

    @Override
    public int hashCode() {
        int result = user_id != null ? user_id.hashCode() : 0;
        result = 31 * result + (room_Number != null ? room_Number.hashCode() : 0);
        result = 31 * result + (date_start != null ? date_start.hashCode() : 0);
        result = 31 * result + (date_end != null ? date_end.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User ID: " + user_id +
                ", Room Number: " + room_Number +
                ", Date of start:" + date_start +
                ", Date of Finish: " + date_end +
                "\n";
    }

    public Integer getUser_id() {
        return user_id;
    }

    public Integer getRoom_Number() {
        return room_Number;
    }

    public Date getDate_start() {
        return date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public Integer getId() {
        return id;
    }

    public Integer getHotel_id() {
        return hotel_id;
    }
}
