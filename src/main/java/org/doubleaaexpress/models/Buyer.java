package org.doubleaaexpress.models;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.doubleaaexpress.models.dao.BuyerDAO;

public class Buyer extends User {

    public Buyer() {
        super();
    }

    public Buyer(Long id, String firstName, String lastName, String phoneNumber, String address, LocalDate birthDate,
            String email, String password) {
        super(id, firstName, lastName, phoneNumber, address, birthDate, email, password);
    }

    /**
     * Populates the buyer table with data from the database.
     *
     * @param table The table to be populated
     */
    public void populateBuyerTable(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        BuyerDAO buyerDAO = new BuyerDAO();
        List<Buyer> buyers = buyerDAO.getAll();

        model.addColumn("Id");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Phone Number");
        model.addColumn("Address");
        model.addColumn("Birth Date");
        model.addColumn("Email");

        for (int i = 0; i < buyers.size(); i++) {
            Buyer buyer = buyers.get(i);
            model.addRow(new Object[] {});
            model.setValueAt(buyer.getId(), i, 0);
            model.setValueAt(buyer.getFirstName(), i, 1);
            model.setValueAt(buyer.getLastName(), i, 2);
            model.setValueAt(buyer.getPhoneNumber(), i, 3);
            model.setValueAt(buyer.getAddress(), i, 4);
            model.setValueAt(buyer.getBirthDate(), i, 5);
            model.setValueAt(buyer.getEmail(), i, 6);
        }
        table.setModel(model);
    }
}
