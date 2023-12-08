package org.doubleaaexpress.models;

/**
 * A functional interface that represents a discount.
 *
 */
@FunctionalInterface
public interface Discount {

    /**
     * Calculates the discount to be applied to a given price.
     *
     * @param price the original price
     * @return the amount of the discount
     */
    double calculateDiscount(double price);
}
