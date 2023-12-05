package org.doubleaaexpress.models.dao.abstractfactory;

import org.doubleaaexpress.models.Administrator;
import org.doubleaaexpress.models.Customer;
import org.doubleaaexpress.models.OrderManager;
import org.doubleaaexpress.models.dao.AdministratorDAO;
import org.doubleaaexpress.models.dao.CustomerDAO;
import org.doubleaaexpress.models.dao.OrderManagerDAO;

/**
 * This class extends AbstractFactoryDAO and provides a concrete implementation
 * of the abstract factory pattern.
 */
public class ConcreteAbstractFactoryDAO extends AbstractFactoryDAO {

    /**
     * This method overrides the getAdministratorDAO method from the
     * AbstractFactoryDAO class.
     * It returns an instance of the AdministratorDAO class.
     *
     * @return an instance of the AdministratorDAO class.
     */
    @Override
    public GenericUserDAO<Administrator> getAdministratorDAO() {
        return new AdministratorDAO();
    }

    @Override
    public GenericUserDAO<OrderManager> getOrderManagerDAO() {
        return new OrderManagerDAO();
    }

    @Override
    public GenericUserDAO<Customer> getCustomerDAO() {
        return new CustomerDAO();
    }
}
