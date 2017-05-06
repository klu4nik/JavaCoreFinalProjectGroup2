package Entity;

import java.io.Serializable;

/**
 * Created by MYKOLA.GOROKHOV on 21.04.2017.
 */
public class Hotel implements Serializable {
    private Integer id;
    private String hotelName;
    private String city;

    public Hotel(String hotelName, String city) {
        this.hotelName = hotelName;
        this.city = city;
        this.id = this.hashCode();
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", hotelName='" + hotelName + '\'' +
                ", city='" + city + '\'' +
                '}' + "\n";
    }

    public Integer getId() {
        return id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (id != null ? !id.equals(hotel.id) : hotel.id != null) return false;
        if (!hotelName.equals(hotel.hotelName)) return false;
        return city.equals(hotel.city);
    }

    @Override
    public int hashCode() {
        int result = hotelName.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }
}
