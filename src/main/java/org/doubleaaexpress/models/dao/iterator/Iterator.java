package org.doubleaaexpress.models.dao.iterator;

public interface Iterator<T> {

    /**
     * Checks if there are more items in the collection.
     * 
     * @return {@code true} if there are more elements, {@code false} otherwise
     */
    boolean hasNext();

    /**
     * Returns the next element in the collection.
     * 
     * @return the next element
     */
    T next();

    /**
     * Removes the last element that was returned by the {@code next()} method.
     */
    void remove();
}
