package org.doubleaaexpress.models;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.doubleaaexpress.models.dao.OrderManagerDAO;

public class OrderManager extends User {

    public OrderManager() {
        super();
    }

    public OrderManager(Long id, String firstName, String lastName, String phoneNumber, String address,
            LocalDate birthDate, String email, String password) {
        super(id, firstName, lastName, phoneNumber, address, birthDate, email, password);
    }

    /**
     * Populates the order manager table with data from the database.
     *
     * @param table The table to be populated
     */
    public void populateAdministratorTable(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        OrderManagerDAO orderManagerDAO = new OrderManagerDAO();
        List<OrderManager> orderManagers = orderManagerDAO.getAll();

        model.addColumn("Id");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Phone Number");
        model.addColumn("Address");
        model.addColumn("Birth Date");
        model.addColumn("Email");

        for (int i = 0; i < orderManagers.size(); i++) {
            OrderManager orderManager = orderManagers.get(i);
            model.addRow(new Object[] {});
            model.setValueAt(orderManager.getId(), i, 0);
            model.setValueAt(orderManager.getFirstName(), i, 1);
            model.setValueAt(orderManager.getLastName(), i, 2);
            model.setValueAt(orderManager.getPhoneNumber(), i, 3);
            model.setValueAt(orderManager.getAddress(), i, 4);
            model.setValueAt(orderManager.getBirthDate(), i, 5);
            model.setValueAt(orderManager.getEmail(), i, 6);
        }
        table.setModel(model);
    }
}
