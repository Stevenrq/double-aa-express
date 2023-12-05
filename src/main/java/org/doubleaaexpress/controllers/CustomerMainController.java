package org.doubleaaexpress.controllers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.doubleaaexpress.models.Customer;
import org.doubleaaexpress.models.Motorcycle;
import org.doubleaaexpress.views.CustomerMainView;
import org.doubleaaexpress.views.MainView;
import org.doubleaaexpress.views.tables.RegisteredMotorcyclesTableView;

import javax.swing.*;

@NoArgsConstructor
@Getter
@Setter
public class CustomerMainController {

    private Customer customer;
    private Motorcycle motorcycle;

    private MainView mainView;
    private CustomerMainView customerMainView;
    private RegisteredMotorcyclesTableView registeredMotorcyclesTableView;


    public CustomerMainController(Customer customer, Motorcycle motorcycle, MainView mainView,
                                  CustomerMainView customerMainView,
                                  RegisteredMotorcyclesTableView registeredMotorcyclesTableView) {
        this.customer = customer;
        this.motorcycle = motorcycle;
        this.mainView = mainView;
        this.customerMainView = customerMainView;
        this.registeredMotorcyclesTableView = registeredMotorcyclesTableView;

        this.motorcycle.populateMotorcycleTable(getRegisteredMotorcyclesTableView().gettRegisteredMotorcycles());
    }

    public void showRegisteredMotorcyclesTable() {
        if (getRegisteredMotorcyclesTableView().gettRegisteredMotorcycles().getRowCount() == 0)
            JOptionPane.showMessageDialog(null, "There are no registered motorcycles");
        else
            getRegisteredMotorcyclesTableView().setVisible(true);
    }
}
