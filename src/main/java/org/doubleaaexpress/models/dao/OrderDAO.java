package org.doubleaaexpress.models.dao;

import org.doubleaaexpress.models.Customer;
import org.doubleaaexpress.models.Order;
import org.doubleaaexpress.models.Product;
import org.doubleaaexpress.util.DBConnection;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private final Connection connection = DBConnection.getInstance().getConnection();

    public void add(Order order) {
        PreparedStatement preparedStatement;
        String sql = "INSERT INTO orders (order_id, date, customer, address, product, quantity, unit_price, " +
                "total_price, status) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, order.getId());
            preparedStatement.setDate(2, Date.valueOf(order.getDate()));
            preparedStatement.setObject(3, order.getCustomer().getFirstName() + " "
                    + order.getCustomer().getLastName());
            preparedStatement.setString(4, order.getAddress());
            preparedStatement.setObject(5, order.getProduct().getName());
            preparedStatement.setInt(6, order.getQuantity());
            preparedStatement.setDouble(7, order.getUnitPrice());
            preparedStatement.setDouble(8, order.getTotalPrice());
            preparedStatement.setString(9, order.getStatus());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error adding order: " + e.getMessage());
        }
    }

    public Order getById(Long id) {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Order order = new Order();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order.setId(resultSet.getLong("order_id"));
                order.setDate(resultSet.getDate("date").toLocalDate());
                order.setCustomer((Customer) resultSet.getObject("customer"));
                order.setAddress(resultSet.getString("address"));
                order.setProduct((Product) resultSet.getObject("product"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setUnitPrice(resultSet.getDouble("unit_price"));
                order.setTotalPrice(resultSet.getDouble("total_price"));
                order.setStatus(resultSet.getString("status"));
            } else
                JOptionPane.showMessageDialog(null, "The order is not registered");
        } catch (SQLException e) {
            System.out.println("Error getting order: " + e.getMessage());
        }
        return order;
    }

    public void update(Order order) {
        PreparedStatement preparedStatement;
        String sql = "UPDATE orders SET date = ?, customer = ?, address = ?, product = ?, quantity = ?, " +
                "unit_price = ?, total_price = ?, status = ? WHERE order_id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(order.getDate()));
            preparedStatement.setObject(2, order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName());
            preparedStatement.setString(3, order.getAddress());
            preparedStatement.setObject(4, order.getProduct().getName());
            preparedStatement.setInt(5, order.getQuantity());
            preparedStatement.setDouble(6, order.getUnitPrice());
            preparedStatement.setDouble(7, order.getTotalPrice());
            preparedStatement.setString(8, order.getStatus());
            preparedStatement.setLong(9, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating order: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        PreparedStatement preparedStatement;
        String sql = "DELETE FROM orders WHERE order_id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error deleting order: " + e.getMessage());
        }
    }

    public List<Order> getAll() {
        PreparedStatement preparedStatement;
        String sql = "SELECT * FROM orders";
        ResultSet resultSet;
        List<Order> orders = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("order_id"));
                order.setDate(resultSet.getDate("date").toLocalDate());
                order.setCustomer((Customer) resultSet.getObject("customer"));
                order.setAddress(resultSet.getString("address"));
                order.setProduct((Product) resultSet.getObject("product"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setUnitPrice(resultSet.getDouble("unit_price"));
                order.setTotalPrice(resultSet.getDouble("total_price"));
                order.setStatus(resultSet.getString("status"));
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all orders: " + e.getMessage());
        }
        return orders;
    }
}
