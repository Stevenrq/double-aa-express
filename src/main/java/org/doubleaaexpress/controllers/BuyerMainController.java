package org.doubleaaexpress.controllers;

import javax.swing.JOptionPane;

import org.doubleaaexpress.models.Buyer;
import org.doubleaaexpress.models.Motorcycle;
import org.doubleaaexpress.views.BuyerMainView;
import org.doubleaaexpress.views.MainView;
import org.doubleaaexpress.views.tables.RegisteredMotorcyclesTableView;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BuyerMainController {

    private Buyer buyer;
    private Motorcycle motorcycle;

    private MainView mainView;
    private BuyerMainView buyerMainView;
    private RegisteredMotorcyclesTableView registeredMotorcyclesTableView;

    public BuyerMainController(Buyer buyer, Motorcycle motorcycle, MainView mainView,
            BuyerMainView buyerMainView,
            RegisteredMotorcyclesTableView registeredMotorcyclesTableView) {
        this.buyer = buyer;
        this.motorcycle = motorcycle;
        this.mainView = mainView;
        this.buyerMainView = buyerMainView;
        this.registeredMotorcyclesTableView = registeredMotorcyclesTableView;

        this.motorcycle.populateMotorcycleTable(getRegisteredMotorcyclesTableView().gettRegisteredMotorcycles());
    }

    public void showRegisteredMotorcyclesTable() {
        if (getRegisteredMotorcyclesTableView().gettRegisteredMotorcycles().getRowCount() == 0)
            JOptionPane.showMessageDialog(null, "There are no registered motorcycles");
        else
            getRegisteredMotorcyclesTableView().setVisible(true);
        getRegisteredMotorcyclesTableView().getbBuyMotorcycle().setVisible(true);
    }
}
