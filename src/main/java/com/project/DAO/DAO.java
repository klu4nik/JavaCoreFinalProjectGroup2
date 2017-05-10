package DAO;

import java.io.IOException;

/**
 * @version final :)
 *          Generic Interface. Used for working with DBs.
 */

public interface DAO<T> {
    /**
     * Used for read from DB
     *
     * @return Generic <T>
     * @throws IOException, ClassNotFoundException
     */
    T get() throws IOException, ClassNotFoundException;

    /**
     * Used for write to DB
     *
     * @param t Generic <T>
     * @throws IOException
     */
    void set(T t) throws IOException;

}
