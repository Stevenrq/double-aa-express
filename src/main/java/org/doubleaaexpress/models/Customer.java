package org.doubleaaexpress.models;

import org.doubleaaexpress.models.dao.CustomerDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;

public class Customer extends User {

    public Customer() {
        super();
    }

    public Customer(Long id, String firstName, String lastName, String phoneNumber, String address, LocalDate birthDate,
            String email, String password) {
        super(id, firstName, lastName, phoneNumber, address, birthDate, email, password);
    }

    /**
     * Populates the customer table with data from the database.
     *
     * @param table The table to be populated
     */
    public void populateCustomerTable(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        CustomerDAO customerDAO = new CustomerDAO();
        List<Customer> customers = customerDAO.getAll();

        model.addColumn("Id");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Phone Number");
        model.addColumn("Address");
        model.addColumn("Birth Date");
        model.addColumn("Email");

        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            model.addRow(new Object[] {});
            model.setValueAt(customer.getId(), i, 0);
            model.setValueAt(customer.getFirstName(), i, 1);
            model.setValueAt(customer.getLastName(), i, 2);
            model.setValueAt(customer.getPhoneNumber(), i, 3);
            model.setValueAt(customer.getAddress(), i, 4);
            model.setValueAt(customer.getBirthDate(), i, 5);
            model.setValueAt(customer.getEmail(), i, 6);
        }
        table.setModel(model);
    }
}
