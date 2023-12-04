package org.doubleaaexpress.models.dao.abstractfactory;

import java.util.List;

/**
 * The interface defines methods for adding, retrieving, updating, and deleting
 * objects of type T.
 *
 * @param <T> the type of the object this DAO will manage
 */
public interface GenericDAO<T> {

    void add(T t);

    T getById(Long id);

    void update(T t);

    void delete(Long id);

    List<T> getAll();

    boolean signIn(T t);

    boolean get(String email, String password);
}
