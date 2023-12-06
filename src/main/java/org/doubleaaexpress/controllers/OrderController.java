package org.doubleaaexpress.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.doubleaaexpress.controllers.forms.SignInFormController;
import org.doubleaaexpress.models.Customer;
import org.doubleaaexpress.models.Motorcycle;
import org.doubleaaexpress.models.Order;
import org.doubleaaexpress.models.dao.MotorcycleDAO;
import org.doubleaaexpress.models.dao.OrderDAO;
import org.doubleaaexpress.views.tables.RegisteredMotorcyclesTableView;
import org.doubleaaexpress.views.tables.RegisteredOrdersTableView;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderController {

    private Customer customer;
    private Motorcycle motorcycle;

    private RegisteredMotorcyclesTableView registeredMotorcyclesTableView;
    private RegisteredOrdersTableView registeredOrdersTableView;

    private SignInFormController signInFormController;

    public OrderController(Customer customer, Motorcycle motorcycle,
            RegisteredMotorcyclesTableView registeredMotorcyclesTableView,
            RegisteredOrdersTableView registeredOrdersTableView) {
        this.customer = customer;
        this.motorcycle = motorcycle;
        this.registeredMotorcyclesTableView = registeredMotorcyclesTableView;
        this.registeredOrdersTableView = registeredOrdersTableView;
    }

    /**
     * Gets the selected motorcycle.
     * 
     * @param row the row of the motorcycle in the table
     * @return the selected motorcycle
     */
    public Motorcycle getSelectedMotorcycle(int row) {
        MotorcycleDAO motorcycleDAO = new MotorcycleDAO();
        List<Motorcycle> motocycles = motorcycleDAO.getAll();
        int i = 0;

        while ((!motocycles.isEmpty()) && i < row) {
            motocycles.get(i);
            i++;
        }
        return motocycles.get(i);
    }

    /**
     * This method registers the order in the database and in the orders table.
     */
    public void registerOrder() {
        MotorcycleDAO motorcycleDAO = new MotorcycleDAO();
        OrderDAO orderDAO = new OrderDAO();

        if (getRegisteredMotorcyclesTableView().gettRegisteredMotorcycles().getSelectedRow() != -1) {
            if (getSignInFormController().getCustomer() != null) {
                customer = getSignInFormController().getCustomer();

                long orderId = (long) (Math.random() * 1000);
                LocalDate orderDate = LocalDate.now();

                String orderAddress = getCustomer().getAddress();
                int selectedRow = getRegisteredMotorcyclesTableView().gettRegisteredMotorcycles().getSelectedRow();
                motorcycle = getSelectedMotorcycle(selectedRow);

                // the data of the selected motorcycle is obtained to create the order.
                long id = Long.parseLong(getRegisteredMotorcyclesTableView().gettRegisteredMotorcycles()
                        .getValueAt(selectedRow, 0).toString());
                String name = getRegisteredMotorcyclesTableView().gettRegisteredMotorcycles()
                        .getValueAt(selectedRow, 1).toString();
                double price = Double.parseDouble(getRegisteredMotorcyclesTableView().gettRegisteredMotorcycles()
                        .getValueAt(selectedRow, 2).toString());
                int quantity = Integer.parseInt(getRegisteredMotorcyclesTableView().gettRegisteredMotorcycles()
                        .getValueAt(selectedRow, 3).toString());
                String model = getRegisteredMotorcyclesTableView().gettRegisteredMotorcycles()
                        .getValueAt(selectedRow, 4).toString();
                short year = Short.parseShort(getRegisteredMotorcyclesTableView().gettRegisteredMotorcycles()
                        .getValueAt(selectedRow, 5).toString());
                String plateNumber = getRegisteredMotorcyclesTableView().gettRegisteredMotorcycles()
                        .getValueAt(selectedRow, 6).toString();
                String status = getRegisteredMotorcyclesTableView().gettRegisteredMotorcycles()
                        .getValueAt(selectedRow, 7).toString();

                int quantityToBuy = Integer
                        .parseInt(JOptionPane
                                .showInputDialog("Enter the quantity of motorcycles you want to purchase: "));
                // conditions necessary to make the purchase
                if (quantityToBuy > quantity) {
                    JOptionPane.showMessageDialog(null, "There are not enough motorcycles in stock");
                    return;
                } else if (quantityToBuy == 0 || quantityToBuy < 0) {
                    JOptionPane.showMessageDialog(null, "You must enter an amount greater than 0");
                    return;
                } else if (quantityToBuy == quantity) {
                    motorcycleDAO.deleteMotorcycle(getMotorcycle().getId());
                    DefaultTableModel defaultTableModel = (DefaultTableModel) getRegisteredMotorcyclesTableView()
                            .gettRegisteredMotorcycles().getModel();
                    defaultTableModel.removeRow(selectedRow);
                    motorcycle.populateMotorcycleTable(getRegisteredMotorcyclesTableView().gettRegisteredMotorcycles());
                } else {
                    getMotorcycle().setQuantity(quantity - quantityToBuy);
                    motorcycleDAO.updateMotorcycle(getMotorcycle().getId(), getMotorcycle().getQuantity());
                    motorcycle.populateMotorcycleTable(getRegisteredMotorcyclesTableView().gettRegisteredMotorcycles());
                }

                // unit price of the selected product
                double unitPrice = getMotorcycle().getPrice();
                double totalPrice = unitPrice * quantityToBuy;
                String orderStatus = "In preparation";

                Order order = new Order(
                        orderId,
                        orderDate,
                        customer,
                        orderAddress,
                        new Motorcycle(id, name, price, quantity, model, year, plateNumber, status),
                        quantityToBuy,
                        unitPrice,
                        totalPrice,
                        orderStatus);

                orderDAO.add(order);
                order.populateOrderTable(getRegisteredOrdersTableView().gettRegisteredOrders());
                motorcycle.populateMotorcycleTable(getRegisteredMotorcyclesTableView().gettRegisteredMotorcycles());
            } else {
                JOptionPane.showMessageDialog(null, "You must log in to purchase",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "You must select the product you want to purchase!");
        }
    }
}
