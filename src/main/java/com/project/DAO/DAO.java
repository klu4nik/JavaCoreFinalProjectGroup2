package DAO;

import Entity.Booking;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by MYKOLA.GOROKHOV on 26.04.2017.
 */
public interface DAO<T> {
    public T get() throws IOException, ClassNotFoundException;

    public void set(T t) throws IOException;

}
