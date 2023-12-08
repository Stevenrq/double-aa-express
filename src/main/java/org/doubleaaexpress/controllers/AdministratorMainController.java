package org.doubleaaexpress.controllers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.doubleaaexpress.models.Administrator;
import org.doubleaaexpress.models.Buyer;
import org.doubleaaexpress.models.Customer;
import org.doubleaaexpress.models.Motorcycle;
import org.doubleaaexpress.models.Order;
import org.doubleaaexpress.models.OrderManager;
import org.doubleaaexpress.views.AdministratorMainView;
import org.doubleaaexpress.views.MainView;
import org.doubleaaexpress.views.RegisteredUsersView;
import org.doubleaaexpress.views.tables.RegisteredAdministratorsTableView;
import org.doubleaaexpress.views.tables.RegisteredBuyersTableView;
import org.doubleaaexpress.views.tables.RegisteredCustomersTableView;
import org.doubleaaexpress.views.tables.RegisteredMotorcyclesTableView;
import org.doubleaaexpress.views.tables.RegisteredOrderManagersTableView;
import org.doubleaaexpress.views.tables.RegisteredOrdersTableView;

import javax.swing.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class AdministratorMainController {

    private Administrator administrator;
    private OrderManager orderManager;
    private Customer customer;
    private Motorcycle motorcycle;
    private Buyer buyer;
    private Order order;

    private MainView mainView;
    private AdministratorMainView administratorMainView;
    private RegisteredUsersView registeredUsersView;
    private RegisteredAdministratorsTableView registeredAdministratorsTableView;
    private RegisteredOrderManagersTableView registeredOrderManagersTableView;
    private RegisteredCustomersTableView registeredCustomersTableView;
    private RegisteredMotorcyclesTableView registeredMotorcyclesTableView;
    private RegisteredBuyersTableView registeredBuyersTableView;
    private RegisteredOrdersTableView registeredOrdersTableView;

    public AdministratorMainController(Administrator administrator, OrderManager orderManager, Customer customer,
            Motorcycle motorcycle, Buyer buyer, Order order, MainView mainView,
            AdministratorMainView administratorMainView, RegisteredUsersView registeredUsersView,
            RegisteredAdministratorsTableView registeredAdministratorsTableView,
            RegisteredOrderManagersTableView registeredOrderManagersTableView,
            RegisteredCustomersTableView registeredCustomersTableView,
            RegisteredMotorcyclesTableView registeredMotorcyclesTableView,
            RegisteredBuyersTableView registeredBuyersTableView, RegisteredOrdersTableView registeredOrdersTableView) {

        this.administrator = administrator;
        this.orderManager = orderManager;
        this.customer = customer;
        this.motorcycle = motorcycle;
        this.buyer = buyer;
        this.order = order;
        this.mainView = mainView;
        this.administratorMainView = administratorMainView;
        this.registeredUsersView = registeredUsersView;
        this.registeredAdministratorsTableView = registeredAdministratorsTableView;
        this.registeredOrderManagersTableView = registeredOrderManagersTableView;
        this.registeredCustomersTableView = registeredCustomersTableView;
        this.registeredMotorcyclesTableView = registeredMotorcyclesTableView;
        this.registeredBuyersTableView = registeredBuyersTableView;
        this.registeredOrdersTableView = registeredOrdersTableView;

        // populates the table when starting the application
        this.administrator
                .populateAdministratorTable(getRegisteredAdministratorsTableView().gettRegisteredAdministrators());
        this.orderManager
                .populateAdministratorTable(getRegisteredOrderManagersTableView().gettRegisteredOrderManagers());
        this.customer.populateCustomerTable(getRegisteredCustomersTableView().gettRegisteredCustomers());
        this.motorcycle.populateMotorcycleTable(getRegisteredMotorcyclesTableView().gettRegisteredMotorcycles());
        this.buyer.populateBuyerTable(getRegisteredBuyersTableView().gettRegisteredBuyers());
        this.order.populateOrderTable(getRegisteredOrdersTableView().gettRegisteredOrders());
    }

    public void showRegisteredUsersTables() {
        switch (Objects.requireNonNull(getRegisteredUsersView().getCbRegisteredUsers().getSelectedItem()).toString()) {
            case "Select":
                JOptionPane.showMessageDialog(null, "You must select an option");
                break;
            case "Administrators":
                if (getRegisteredAdministratorsTableView().gettRegisteredAdministrators().getRowCount() == 0)
                    JOptionPane.showMessageDialog(null, "There are no registered administrators");
                else
                    getRegisteredAdministratorsTableView().setVisible(true);
                break;
            case "Order Managers":
                if (getRegisteredOrderManagersTableView().gettRegisteredOrderManagers().getRowCount() == 0)
                    JOptionPane.showMessageDialog(null, "There are no registered order managers");
                else
                    getRegisteredOrderManagersTableView().setVisible(true);
                break;
            case "Customers":
                if (getRegisteredCustomersTableView().gettRegisteredCustomers().getRowCount() == 0)
                    JOptionPane.showMessageDialog(null, "There are no registered customers");
                else
                    getRegisteredCustomersTableView().setVisible(true);
                break;
            case "Motorcycles":
                if (getRegisteredMotorcyclesTableView().gettRegisteredMotorcycles().getRowCount() == 0)
                    JOptionPane.showMessageDialog(null, "There are no registered motorcycles");
                else
                    getRegisteredMotorcyclesTableView().setVisible(true);
                getRegisteredMotorcyclesTableView().getbBuyMotorcycle().setVisible(false);
                break;
            case "Buyers":
                if (getRegisteredBuyersTableView().gettRegisteredBuyers().getRowCount() == 0)
                    JOptionPane.showMessageDialog(null, "There are no registered buyers");
                else
                    getRegisteredBuyersTableView().setVisible(true);
                break;
            case "Orders":
                if (getRegisteredOrdersTableView().gettRegisteredOrders().getRowCount() == 0)
                    JOptionPane.showMessageDialog(null, "There are no registered orders");
                else
                    getRegisteredOrdersTableView().setVisible(true);
                break;
            default:
                // default option
                break;
        }
    }
}
