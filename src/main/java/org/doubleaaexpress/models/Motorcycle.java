package org.doubleaaexpress.models;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.doubleaaexpress.models.dao.AdministratorDAO;
import org.doubleaaexpress.models.dao.MotorcycleDAO;

public class Motorcycle extends Product {

    private String model;
    private Short year;
    private String plateNumber;
    private String status;

    public Motorcycle() {
        super();
    }

    public Motorcycle(Long id, String name, Double price, String model, Short year, String plateNumber, String status) {
        super(id, name, price);
        this.model = model;
        this.year = year;
        this.plateNumber = plateNumber;
        this.status = status;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Populates the motorcycle table with data from the database.
     *
     * @param table The table to be populated
     */
    public void populateAdministratorTable(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        MotorcycleDAO motorcycleDAO = new MotorcycleDAO();
        List<Motorcycle> motorcycles = motorcycleDAO.getAll();

        model.addColumn("Id");
        model.addColumn("Name");
        model.addColumn("Price");
        model.addColumn("Model");
        model.addColumn("Year");
        model.addColumn("PlateNumber");
        model.addColumn("Status");

        for (int i = 0; i < motorcycles.size(); i++) {
            Motorcycle motorcycle = motorcycles.get(i);
            model.addRow(new Object[] {});
            model.setValueAt(motorcycle.getId(), i, 0);
            model.setValueAt(motorcycle.getName(), i, 1);
            model.setValueAt(motorcycle.getPrice(), i, 2);
            model.setValueAt(motorcycle.getModel(), i, 3);
            model.setValueAt(motorcycle.getYear(), i, 4);
            model.setValueAt(motorcycle.getPlateNumber(), i, 5);
            model.setValueAt(motorcycle.getStatus(), i, 6);
        }
        table.setModel(model);
    }
}
