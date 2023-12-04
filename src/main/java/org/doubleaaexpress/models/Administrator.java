package org.doubleaaexpress.models;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.doubleaaexpress.models.dao.AdministratorDAO;

public class Administrator extends User {

    public Administrator() {
        super();
    }

    public Administrator(Long id, String firstName, String lastName, String phoneNumber, LocalDate birthDate,
                         String email, String password) {
        super(id, firstName, lastName, phoneNumber, birthDate, email, password);
    }

    /**
     * Populates the administrator table with data from the database.
     *
     * @param table The JTable to be populated
     */
    public void populateAdministratorTable(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        AdministratorDAO administratorDAO = new AdministratorDAO();
        List<Administrator> administrators = administratorDAO.getAll();

        model.addColumn("Id");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Phone Number");
        model.addColumn("Birth Date");
        model.addColumn("Email");
        model.addColumn("Password");

        for (int i = 0; i < administrators.size(); i++) {
            Administrator administrator = administrators.get(i);
            model.addRow(new Object[]{});
            model.setValueAt(administrator.getId(), i, 0);
            model.setValueAt(administrator.getFirstName(), i, 1);
            model.setValueAt(administrator.getLastName(), i, 2);
            model.setValueAt(administrator.getPhoneNumber(), i, 3);
            model.setValueAt(administrator.getBirthDate(), i, 4);
            model.setValueAt(administrator.getEmail(), i, 5);
            model.setValueAt(administrator.getPassword(), i, 6);
        }
        table.setModel(model);
    }
}
