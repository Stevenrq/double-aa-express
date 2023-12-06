package org.doubleaaexpress.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.doubleaaexpress.models.dao.OrderDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {

    private Long id;
    private LocalDate date;
    private Buyer buyer;
    private String address;
    private Product product;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
    private String status;

    /**
     * Populates the order table with data from the database.
     *
     * @param table The table to be populated
     */
    public void populateOrderTable(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders = orderDAO.getAll();

        model.addColumn("Id");
        model.addColumn("Date");
        model.addColumn("Buyer");
        model.addColumn("Address");
        model.addColumn("Product");
        model.addColumn("Quantity");
        model.addColumn("Unit Price");
        model.addColumn("Total Price");
        model.addColumn("Status");

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);

            model.addRow(new Object[] {});
            model.setValueAt(order.getId(), i, 0);
            model.setValueAt(order.getDate(), i, 1);
            model.setValueAt(order.getBuyer().getFirstName() + " " + order.getBuyer().getLastName(), i, 2);
            model.setValueAt(order.getAddress(), i, 3);
            model.setValueAt(order.getProduct().getName(), i, 4);
            model.setValueAt(order.getQuantity(), i, 5);
            model.setValueAt(order.getUnitPrice(), i, 6);
            model.setValueAt(order.getTotalPrice(), i, 7);
            model.setValueAt(order.getStatus(), i, 8);
        }
        table.setModel(model);
    }
}
