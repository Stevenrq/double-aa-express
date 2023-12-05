package org.doubleaaexpress.models.dao.abstractfactory;

import java.util.List;

/**
 * Abstract Data Access Object (DAO) for managing entities of type {@code T}.
 *
 * @param <T> the type of entity to manage
 */
public interface GenericUserDAO<T> {

    /**
     * Adds a new entity to the data store.
     *
     * @param t the entity to add
     */
    void add(T t);

    /**
     * Retrieves the entity with the specified identifier.
     *
     * @param id the identifier of the entity to retrieve
     * @return the entity with the specified identifier, or {@code null} if no such
     *         entity exists
     */
    T getById(Long id);

    /**
     * Checks if a user exists with the specified email and password.
     *
     * @param email    the user's email address
     * @param password the user's password
     * @return {@code true} if a user exists with the specified email and password,
     *         {@code false} otherwise
     */
    boolean get(String email, String password);

    /**
     * Updates the specified entity in the data store.
     *
     * @param t the entity to update
     */
    void update(T t);

    /**
     * Deletes the entity with the specified identifier from the data store.
     *
     * @param id the identifier of the entity to delete
     */
    void delete(Long id);

    /**
     * Retrieves all entities of type {@code T} from the data store.
     *
     * @return a list of all entities of type {@code T}
     */
    List<T> getAll();

    /**
     * Attempts to sign in a user with the specified credentials.
     *
     * @param t the user credentials
     * @return {@code true} if the user's credentials are valid, {@code false}
     *         otherwise
     */
    boolean signIn(T t);
}
