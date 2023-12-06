package org.doubleaaexpress.controllers.forms;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JOptionPane;

import org.doubleaaexpress.models.Customer;
import org.doubleaaexpress.models.dao.abstractfactory.AbstractFactoryDAO;
import org.doubleaaexpress.models.dao.abstractfactory.ConcreteAbstractFactoryDAO;
import org.doubleaaexpress.models.dao.abstractfactory.GenericUserDAO;
import org.doubleaaexpress.views.MainView;
import org.doubleaaexpress.views.forms.CustomerFormView;
import org.doubleaaexpress.views.tables.RegisteredCustomersTableView;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomerFormController {

    private Customer customer;

    private MainView mainView;
    private CustomerFormView customerFormView;
    private RegisteredCustomersTableView registeredCustomersTableView;

    public CustomerFormController(Customer customer, MainView mainView,
            CustomerFormView customerFormView, RegisteredCustomersTableView registeredCustomersTableView) {
        this.customer = customer;

        this.mainView = mainView;
        this.customerFormView = customerFormView;
        this.registeredCustomersTableView = registeredCustomersTableView;
    }

    public void signUpCustomer() {
        long id = 0L;
        String firstName = "", lastName = "", phoneNumber = "", address = "", email = "", password = "";
        LocalDate birthDate = null, dateToLocalDate;
        boolean v;
        Date d = getCustomerFormView().getDcBirthDate().getDate();

        // create a factory DAOs
        AbstractFactoryDAO abstractFactoryDAO = new ConcreteAbstractFactoryDAO();
        // get a DAO for Customer
        GenericUserDAO<Customer> customerDAO = abstractFactoryDAO.getCustomerDAO();

        if (emptyFields()) {
            v = false;
            JOptionPane.showMessageDialog(null, "The fields cannot be empty");
        } else {
            try {
                id = Long.parseLong(getCustomerFormView().getTfId().getText());
                firstName = getCustomerFormView().getTfFirstName().getText();
                lastName = getCustomerFormView().getTfLastName().getText();
                phoneNumber = getCustomerFormView().getTfPhoneNumber().getText();
                address = getCustomerFormView().getTfAddress().getText();
                dateToLocalDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                birthDate = dateToLocalDate;
                email = getCustomerFormView().getTfEmail().getText();
                password = new String(getCustomerFormView().getPfPassword().getPassword());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "The id must be just numbers");
                return;
            }
            v = true;
            getCustomerFormView().setVisible(false);
            cleanFields();
        }

        if (v) {
            Customer customer = new Customer(id, firstName, lastName, phoneNumber, address, birthDate, email,
                    password);
            customerDAO.add(customer);
            customer.populateCustomerTable(getRegisteredCustomersTableView().gettRegisteredCustomers());
        }
    }

    public void cleanFields() {
        getCustomerFormView().getTfId().setText("");
        getCustomerFormView().getTfFirstName().setText("");
        getCustomerFormView().getTfLastName().setText("");
        getCustomerFormView().getTfPhoneNumber().setText("");
        getCustomerFormView().getTfAddress().setText("");
        getCustomerFormView().getDcBirthDate().setDate(null);
        getCustomerFormView().getTfEmail().setText("");
        getCustomerFormView().getPfPassword().setText("");
    }

    public boolean emptyFields() {
        return getCustomerFormView().getTfId().getText().isBlank() ||
                getCustomerFormView().getTfFirstName().getText().isBlank() ||
                getCustomerFormView().getTfLastName().getText().isBlank() ||
                getCustomerFormView().getTfPhoneNumber().getText().isBlank() ||
                getCustomerFormView().getTfAddress().getText().isBlank() ||
                getCustomerFormView().getDcBirthDate().getDate() == null ||
                getCustomerFormView().getTfEmail().getText().isBlank() ||
                getCustomerFormView().getPfPassword().getPassword().length == 0;
    }
}
