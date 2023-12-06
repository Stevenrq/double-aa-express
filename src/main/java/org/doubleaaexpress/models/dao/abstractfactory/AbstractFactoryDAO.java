package org.doubleaaexpress.models.dao.abstractfactory;

import org.doubleaaexpress.models.Administrator;
import org.doubleaaexpress.models.Buyer;
import org.doubleaaexpress.models.Customer;
import org.doubleaaexpress.models.OrderManager;

/**
 * AbstractFactoryDAO is an abstract class that serves as a factory for creating
 * different types of DAOs.
 * This class follows the Abstract Factory design pattern.
 */
public abstract class AbstractFactoryDAO {

    /**
     * This method is used to get an instance of GenericDAO for the Administrator
     * class.
     * 
     * @return An instance of GenericDAO for the
     *         Administrator class.
     */
    public abstract GenericUserDAO<Administrator> getAdministratorDAO();

    public abstract GenericUserDAO<OrderManager> getOrderManagerDAO();

    public abstract GenericUserDAO<Customer> getCustomerDAO();

    public abstract GenericUserDAO<Buyer> getBuyerDAO();
}